import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<Integer>[] adj;
    private static int[] in;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];

        for (int node = 1; node < N + 1; node++) {
            adj[node] = new ArrayList<>();
        }

        in = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());

            adj[nodeX].add(nodeY);
            in[nodeY]++;
        }
    }

    private static String simulate() {
        Queue<int[]> q = new ArrayDeque<>();

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        for (int node = 1; node < N + 1; node++) {
            if (in[node] == 0) {
                q.add(new int[] {node, 0});
                dist[node] = 0;
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int node = curr[0];
            int d = curr[1];

            for (int next : adj[node]) {
                in[next]--;

                if (in[next] == 0 && dist[next] == -1) {
                    dist[next] = d + 1;
                    q.add(new int[] {next, d + 1});
                }
            }
        }

        for (int node = 1; node < N + 1; node++) {
            if (dist[node] == -1) {
                return "Inconsistent";
            }
        }

        return "Consistent";

    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(simulate());
    }
}