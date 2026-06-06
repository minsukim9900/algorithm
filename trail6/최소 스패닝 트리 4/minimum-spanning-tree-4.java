import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] parent, size;
    private static char[] states;
    private static List<int[]> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];
        states = new char[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int node = 1; node < N + 1; node++) {
            parent[node] = node;
            size[node] = 1;
            states[node] = st.nextToken().charAt(0);
        }

        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (states[nodeX] == states[nodeY]) {
                continue;
            }

            edges.add(new int[] { nodeX, nodeY, weight });
        }

        edges.sort((a, b) -> Integer.compare(a[2], b[2]));

        System.out.println(makeTree());
    }

    private static int makeTree() {
        int pick = 0;
        int answer = 0;

        for (int[] edge : edges) {
            int nodeX = edge[0];
            int nodeY = edge[1];
            int weight = edge[2];

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                pick++;
                answer += weight;
                union(rootX, rootY);

                if (pick == N - 1) {
                    return answer;
                }
            }
        }

        return -1;
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
}
