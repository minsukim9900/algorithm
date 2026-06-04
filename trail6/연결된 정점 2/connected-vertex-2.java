import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] parent, size;

    private static final int MAX_NODE_NUMBER = 100_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[MAX_NODE_NUMBER];
        size = new int[MAX_NODE_NUMBER];

        for (int node = 1; node < MAX_NODE_NUMBER; node++) {
            parent[node] = node;
            size[node] = 1;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if (rootX != rootY) {
                sb.append(union(rootX, rootY)).append("\n");
            } else {
                sb.append(size[rootX]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    private static int union(int rootX, int rootY) {
        size[rootX] += size[rootY];
        parent[rootY] = rootX;
        return size[rootX];
    }
}
