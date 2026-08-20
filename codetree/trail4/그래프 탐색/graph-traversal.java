import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, count;
    private static List<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        
        for (int node = 1; node < N + 1; node++) {
            adj[node] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }

        count = 0;

        dfs(1, new boolean[N + 1]);

        System.out.println(count - 1);
    }

    private static void dfs(int curr, boolean[] visited) {
        visited[curr] = true;
        count++;

        for (int next : adj[curr]) {
            if (visited[next]) {
                continue;
            }

            dfs(next, visited);
        }
    }
}