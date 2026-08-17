import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] dist;
    private static List<int[]>[] adj;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];

        for (int node = 1; node < N + 1; node++) {
            adj[node] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            adj[nodeX].add(new int[] {nodeY, d});
            adj[nodeY].add(new int[] {nodeX, d});
        }

        dist = new int[N + 1];
    }

    private static void dfs(int curr, int cd, boolean[] visited) {
        visited[curr] = true;
        dist[curr] = cd;

        for (int[] edge : adj[curr]) {
            int next = edge[0];

            if (visited[next]) {
                continue;
            }

            int nd = edge[1];

            dfs(next, cd + nd, visited);
        }
    }

    private static int simulate() {
        dist = new int[N + 1];

        dfs(1, 0, new boolean[N + 1]);

        int start = 1;
        int maxDist = 0;

        for (int node = 2; node < N + 1; node++) {
            if (maxDist < dist[node]) {
                start = node;
                maxDist = dist[node];
            }
        }

        dfs(start, 0, new boolean[N + 1]);

        maxDist = 0;

        for (int node = 1; node < N + 1; node++) {
            maxDist = Math.max(maxDist, dist[node]);
        }

        return maxDist;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(simulate());
    }
}