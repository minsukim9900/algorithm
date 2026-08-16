import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] parent;
    private static List<Integer>[] adj;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        parent = new int[N + 1];

        for (int node = 1; node < N + 1; node++) {
            adj[node] = new ArrayList<>();
            parent[node] = node;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());

            adj[nodeX].add(nodeY);
            adj[nodeY].add(nodeX);
        }
    }

    private static void dfs(int curr, boolean[] visited) {
        visited[curr] = true;

        for (int next : adj[curr]) {
            if (visited[next]) {
                continue;
            }

            visited[next] = true;
            parent[next] = curr;

            dfs(next, visited);
        }
    }

    private static String getAnswer() {
        dfs(1, new boolean[N + 1]);

        StringBuilder sb = new StringBuilder();

        for (int node = 2; node < N + 1; node++) {
            sb.append(parent[node]).append("\n");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(getAnswer());
    }
}