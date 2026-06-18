import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] parent, size;
    private static int[] xs, ys;

    private static class Info {
        int nodeX;
        int nodeY;
        double dist;

        public Info(int nodeX, int nodeY, double dist) {
            this.nodeX = nodeX;
            this.nodeY = nodeY;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];
        xs = new int[N + 1];
        ys = new int[N + 1];

        for (int node = 1; node <= N; node++) {
            st = new StringTokenizer(br.readLine());

            xs[node] = Integer.parseInt(st.nextToken());
            ys[node] = Integer.parseInt(st.nextToken());

            parent[node] = node;
            size[node] = 1;
        }

        int pick = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                union(rootX, rootY);
                pick++;
            }
        }

        List<Info> edges = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                long dx = xs[i] - xs[j];
                long dy = ys[i] - ys[j];

                double dist = Math.sqrt(dx * dx + dy * dy);

                edges.add(new Info(i, j, dist));
            }
        }

        edges.sort((a, b) -> Double.compare(a.dist, b.dist));

        double answer = 0;

        for (Info edge : edges) {
            int rootX = findParent(edge.nodeX);
            int rootY = findParent(edge.nodeY);

            if (rootX != rootY) {
                union(rootX, rootY);
                answer += edge.dist;
                pick++;

                if (pick == N - 1) {
                    break;
                }
            }
        }

        System.out.printf("%.2f\n", answer);
    }

    private static int findParent(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    private static void union(int rootX, int rootY) {
        if (size[rootX] >= size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }
}