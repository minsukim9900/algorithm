import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static List<int[]>[] adj;
    
    private static final int START = 1;
    private static final int INF = 100_000_000;

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

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[nodeX].add(new int[] {nodeY, weight});
        }
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];

        Arrays.fill(dist, INF);

        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        pq.add(new int[] {start, dist[start]});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int node = curr[0];
            int d = curr[1];

            if (d != dist[node]) {
                continue;
            }

            for (int[] next : adj[node]) {
                int nextNode = next[0];
                int weight = next[1];

                int nextD = d + weight;

                if (dist[nextNode] > nextD) {
                    dist[nextNode] = nextD;
                    pq.add(new int[] {nextNode, nextD});
                }
            }
        }

        return dist;
    }

    private static String getAnswer(int[] dist) {
        StringBuilder sb = new StringBuilder();

        for (int node = 2; node < N + 1; node++) {
            int d = dist[node] == INF ? -1 : dist[node];

            sb.append(d).append("\n");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        int[] dist = dijkstra(START);

        System.out.println(getAnswer(dist));
    }
}