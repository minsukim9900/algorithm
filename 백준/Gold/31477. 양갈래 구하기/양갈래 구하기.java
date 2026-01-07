import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static List<int[]>[] adj;
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[x].add(new int[]{y, w});
            adj[y].add(new int[]{x, w});
        }

        int result = dfs(1, INF, new boolean[N + 1]);
        System.out.println(result == INF ? 0 : result);
    }

    private static int dfs(int node, int weight, boolean[] visited) {
        visited[node] = true;

        int child = 0;
        for (int[] next : adj[node]) {
            if (visited[next[0]]) continue;
            child += dfs(next[0], next[1], visited);
        }
        return child == 0 ? weight : Math.min(weight, child);
    }
}