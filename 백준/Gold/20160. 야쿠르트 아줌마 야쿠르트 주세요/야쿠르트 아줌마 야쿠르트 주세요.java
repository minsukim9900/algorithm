import java.io.*;
import java.util.*;

public class Main {
    static final long INF = 987654321987654321L;
    static int V, E, ps;
    static ArrayList<long[]>[] adj;   // 각 원소: {다음 정점, 가중치}
    static int[] info = new int[11];    // 야쿠르트 아줌마가 방문할 10개 지점 (1~10)
    static long[] milkTime = new long[11];  // milkTime[i]: 아줌트가 info[i]에 도착하는 누적 시각

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        // 그래프 초기화 (정점 번호 1~V)
        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++){
            adj[i] = new ArrayList<>();
        }
        
        // 간선 정보 입력 (양방향, 가중치는 long)
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            adj[u].add(new long[]{v, w});
            adj[v].add(new long[]{u, w});
        }
        
        // 야쿠르트 아줌마가 방문할 10개 지점 입력 (info[1] ~ info[10])
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 10; i++){
            info[i] = Integer.parseInt(st.nextToken());
        }
        
        // milkTime 배열 초기화: 첫 지점(info[1])에서 출발 시간 0
        Arrays.fill(milkTime, 0);
        milkTime[1] = 0;
        
        // 야쿠르트 아줌마의 동선 계산
        // "만약 i번째 지점에서 i+1번째 지점으로 이동 가능한 경로가 없다면,
        //  i+2, i+3, ... (최초로 도달 가능한 지점)으로 이동"
        int current = 1;
        while (current < 10) {
            boolean found = false;
            int nextCandidate = current + 1;
            while (nextCandidate <= 10) {
                long d = dijkstraOneShot(info[current], info[nextCandidate]);
                if (d == INF) {
                    milkTime[nextCandidate] = INF;
                    nextCandidate++;
                } else {
                    milkTime[nextCandidate] = milkTime[current] + d;
                    found = true;
                    break;
                }
            }
            if (!found) break; // 더 이상 이동 가능한 지점이 없으면 종료
            current = nextCandidate; // 다음 방문 지점으로 전환
        }
        
        // 내 출발 정점
        ps = Integer.parseInt(br.readLine());
        // 내 출발점 ps에서 모든 정점까지의 최단 거리 계산
        long[] myDist = findShortest(ps);
        
        // 구매 가능한 노드 판별:
        // 야쿠르트 아줌마가 해당 지점(info 배열에 포함된 노드)에 도착하는 시각(milkTime[i])
        // 가 내 도착 시각(myDist[info[i]])보다 같거나 늦으면 구매 가능.
        // 문제에서 "가장 작은 노드"를 요구하므로, info 배열 내 모든 노드를 검사 후 최소 노드 번호를 선택.
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= 10; i++){
            int node = info[i];
            if(myDist[node] == INF) continue; // 도달 불가면 패스
            if (milkTime[i] >= myDist[node] && milkTime[i] < INF) {
                ans = Math.min(ans, node);
            }
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
    
    // 내 출발점에서 각 정점까지 최단 거리(long형) 다익스트라
    private static long[] findShortest(int start) {
        long[] dist = new long[V + 1];
        Arrays.fill(dist, INF);
        boolean[] visited = new boolean[V + 1];
        dist[start] = 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{start, 0});
        
        while (!pq.isEmpty()){
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long cost = cur[1];
            if (visited[u]) continue;
            visited[u] = true;
            for (long[] edge : adj[u]) {
                int v = (int) edge[0];
                long w = edge[1];
                if (dist[v] > cost + w) {
                    dist[v] = cost + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
    
    // 단발성 다익스트라: start에서 end까지의 최단 거리 반환 (도달 불가 시 INF)
    private static long dijkstraOneShot(int start, int end) {
        long[] dist = new long[V + 1];
        Arrays.fill(dist, INF);
        boolean[] visited = new boolean[V + 1];
        dist[start] = 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{start, 0});
        
        while (!pq.isEmpty()){
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long cost = cur[1];
            if (visited[u]) continue;
            visited[u] = true;
            if (u == end) return cost;
            for (long[] edge : adj[u]) {
                int v = (int) edge[0];
                long w = edge[1];
                if (dist[v] > cost + w) {
                    dist[v] = cost + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }
        return INF;
    }
}