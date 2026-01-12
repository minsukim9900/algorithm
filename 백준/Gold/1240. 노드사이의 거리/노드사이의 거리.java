import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, result;
    private static List<int[]>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[x].add(new int[] {y, w});
            adj[y].add(new int[] {x, w});
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            result = 0;
            dfs(start, 0, end, new boolean[N + 1]);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int node, int count, int end, boolean[] visited) {
        visited[node] = true;

        if(node == end) {
            result = count;
            return;
        }

        for(int[] next : adj[node]) {
            if(visited[next[0]]) {
                continue;
            }

            dfs(next[0], count + next[1], end, visited);
        }
    }
}