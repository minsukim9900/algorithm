import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] nums;
    private static List<Integer>[] adj;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        dp = new int[N + 1][2];

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int total = 0;
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            total += nums[i];
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }
        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node, int pre) {
        dp[node][1] = nums[node];

        for (int next : adj[node]) {
            if (next == pre) continue;

            dfs(next, node);
            dp[node][1] += dp[next][0];
            dp[node][0] += Math.max(dp[next][0], dp[next][1]);
        }
    }
}