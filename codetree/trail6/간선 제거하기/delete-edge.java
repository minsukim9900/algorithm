import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, total;
    private static int[] parent, size;
    private static int[][] edges;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        total = 0;

        parent = new int[N + 1];
        size = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        edges = new int[M][3];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i][0] = nodeX;
            edges[i][1] = nodeY;
            edges[i][2] = weight;

            total += weight;
        }
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
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

    private static int solution() {
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        int sum = 0;
        int pick = 0;

        for (int i = 0; i < M; i++) {
            int nodeX = edges[i][0];
            int nodeY = edges[i][1];
            int weight = edges[i][2];

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                union(rootX, rootY);
                pick++;
                sum += weight;

                if (pick == N - 1) {
                    break;
                }
            }
        }

        return total - sum;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(solution());
    }
}