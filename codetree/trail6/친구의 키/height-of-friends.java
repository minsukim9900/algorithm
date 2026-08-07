import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] in;
    private static List<Integer>[] adj;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        in = new int[N + 1];

        adj = new ArrayList[N + 1];

        for (int node = 1; node < N + 1; node++) {
            adj[node] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            in[b]++;

            adj[a].add(b);
        }
    }

    private static String bfs() {
        StringBuilder sb = new StringBuilder();

        Queue<Integer> q = new ArrayDeque<>();

        for (int num = 1; num < N + 1; num++) {
            if (in[num] == 0) {
                q.add(num);
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();

            sb.append(curr).append(" ");

            for (int next : adj[curr]) {
                in[next]--;

                if (in[next] == 0) {
                    q.add(next);
                }
            }
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(bfs());
    }
}