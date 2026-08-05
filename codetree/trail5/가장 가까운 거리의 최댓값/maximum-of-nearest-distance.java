import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, A, B, C;
    private static List<int[]>[] adj;

    private static final int INF = 1_000_000_000;

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

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int nodeX = Integer.parseInt(st.nextToken());
            int nodeY = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[nodeX].add(new int[] {nodeY, weight});
            adj[nodeY].add(new int[] {nodeX, weight});
        }
    }

    private static int[] dijkstra() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[A] = 0;
        dist[B] = 0;
        dist[C] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        pq.add(new int[] {A, dist[A]});
        pq.add(new int[] {B, dist[B]});
        pq.add(new int[] {C, dist[C]});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int node = curr[0];
            int d = curr[1];

            if (dist[node] != d) {
                continue;
            }

            for (int[] edgeInfo : adj[node]) {
                int next = edgeInfo[0];
                int nd = d + edgeInfo[1];

                if (dist[next] > nd) {
                    dist[next] = nd;
                    pq.add(new int[] {next, nd});
                }
            }
        }

        return dist;
    }

    private static int cal() {
        int[] dist = dijkstra();
        
        int answer = 0;

        for (int node = 1; node < N + 1; node++) {
            if (dist[node] == INF) {
                continue;
            }

            answer = Math.max(answer, dist[node]);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}