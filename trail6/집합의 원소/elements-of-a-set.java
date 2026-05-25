import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] parent, childCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        childCount = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
            childCount[i] = 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int px = findParent(x);
            int py = findParent(y);

            if (command == 0) {
                union(px, py);
            } else {
                sb.append(px == py ? 1 : 0).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static int findParent(int node) {
        if (node != parent[node]) {
            return findParent(parent[node]);
        }

        return parent[node];
    }

    private static void union(int x, int y) {
        if (childCount[x] >= childCount[y]) {
            parent[y] = x;
            childCount[x] += childCount[y];
        } else {
            parent[x] = y;
            childCount[y] += childCount[x];
        }
    }
}
