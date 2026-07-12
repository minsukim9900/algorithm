import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] parent, size;
    private static int[][] edges;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];

        for (int node = 1; node < N + 1; node++) {
            parent[node] = node;
            size[node] = 1;
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
        }
    }

    private static int findParent(int nodeX) {

        if (nodeX == parent[nodeX]) {
            return nodeX;
        }

        return parent[nodeX] = findParent(parent[nodeX]);
    }

    private static void union(int rootX, int rootY) {

        if (size[rootX] >= size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] = size[rootX];
        }
    }

    private static int solution() {
        Arrays.sort(edges, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        int pick = 0;
        int sum = 0;
        int lastWeight = 0;

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
                lastWeight = weight;

                if (pick == N - 1) {
                    break;
                }
            }
        }

        return sum - lastWeight;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(solution());
    }
}