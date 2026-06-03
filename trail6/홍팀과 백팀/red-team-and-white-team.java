import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] parent, diff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        diff = new int[N + 1];
        for (int node = 1; node < N + 1; node++) {
            parent[node] = node;
            diff[node] = 0;
        }

        int answer = 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX == rootY && (diff[nodeX] ^ diff[nodeY]) == 0) {
                answer = 0;
                break;
            } else if (rootX != rootY) {
                union(rootX, rootY);
                diff[rootY] = diff[nodeX] ^ diff[nodeY] ^ 1;
            }
        }
        System.out.println(answer);
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }
        int originalParent = parent[node];
        parent[node] = findParent(parent[node]);

        diff[node] ^= diff[originalParent];

        return parent[node];
    }

    private static void union(int nodeX, int nodeY) {
        parent[nodeY] = nodeX;
    }
}
