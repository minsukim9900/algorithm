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
        Queue<Integer> q = new ArrayDeque<>();

        for (int node = 1; node < N + 1; node++) {
            if (in[node] == 0) {
                q.add(node);
            }
        }

        int count = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();

            count++;

            for (int next : adj[curr]) {
                in[next]--;

                if (in[next] == 0) {
                    q.add(next);
                }
            }
        }

        return count == N ? "Consistent" : "Inconsistent";
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(simulate());
    }
}