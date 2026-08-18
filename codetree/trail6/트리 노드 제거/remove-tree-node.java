import java.io.*;
import java.util.*;

public class Main {
    private static int N, start, M;
    private static List<Integer>[] adj;
    private static int[] count;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N];

        for (int node = 0; node < N; node++) {
            adj[node] = new ArrayList<>();
        }

        count = new int[N];

        start = 0;

        st = new StringTokenizer(br.readLine());
        for (int node = 0; node < N; node++) {
            int p = Integer.parseInt(st.nextToken());

            if (p == -1) {
                start = node;
                continue;
            }

            adj[p].add(node);
        }

        M = Integer.parseInt(br.readLine());
    }

    private static int dfs(int curr) {
        int cnt = 0;

        boolean flag = true;
        for (int next : adj[curr]) {
            if (next == M) {
                continue;
            }

            cnt += dfs(next);
            flag = false;
        }

        if (flag) {
            cnt = 1;
        }

        count[curr] = cnt;
        return cnt;
    }

    private static int getAnswer() {
        dfs(start);

        return count[start] - count[M];
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(getAnswer());
    }
}