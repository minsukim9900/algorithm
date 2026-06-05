import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] parent, size;
    private static int[][] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
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
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        int pick = 0;
        int answer = 0;

        for (int i = 0; i < M; i++) {
            int nodeX = edges[i][0];
            int nodeY = edges[i][1];
            int weight = edges[i][2];

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                pick++;
                union(rootX, rootY);
                answer += weight;

                if (pick == N - 1) {
                    break;
                }
            }
        }

        System.out.println(answer);
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
            size[rootY] += size[rootY];
        }
    }
}
