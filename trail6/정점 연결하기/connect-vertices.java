import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N - 2; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int px = findParent(x);
            int py = findParent(y);

            if (px != py) {
                union(px, py);
            }
        }

        int firstRoot = findParent(1);

        for(int i = 2; i < N + 1; i++) {
            if(findParent(i) != firstRoot) {
                System.out.println(1 + " " + i);
                return;
            }
        }

        System.out.println(sb.toString());
    }

    private static int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = findParent(parent[x]);
    }

    private static void union(int x, int y) {
        if (y >= x) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
}
