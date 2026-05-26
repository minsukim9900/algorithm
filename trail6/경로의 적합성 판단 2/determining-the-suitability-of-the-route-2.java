import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, k;
    private static int[] parent, childCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        childCount = new int[N + 1];

        for (int num = 1; num < N + 1; num++) {
            parent[num] = num;
            childCount[num] = 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = findParent(Integer.parseInt(st.nextToken()));
            int y = findParent(Integer.parseInt(st.nextToken()));

            if (x != y) {
                union(x, y);
            }
        }

        st = new StringTokenizer(br.readLine());
        int root = findParent(Integer.parseInt(st.nextToken()));

        for (int i = 0; i < k - 1; i++) {
            if (root != findParent(Integer.parseInt(st.nextToken()))) {
                System.out.println(0);
                return;
            }

        }

        System.out.println(1);
    }

    private static int findParent(int x) {
        if (parent[x] == x) {
            return parent[x];
        }

        return parent[x] = findParent(parent[x]);
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
