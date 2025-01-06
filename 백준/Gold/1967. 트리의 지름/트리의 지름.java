import java.io.*;
import java.util.*;

public class Main {

    private static int N;  // 노드 개수
    private static List<int[]>[] node;  // 인접 리스트
    private static boolean[] visited;  // 방문 여부 배열
    private static int maxDist = 0;  // 트리 지름
    private static int maxNode = 0;  // 가장 먼 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());  // 노드의 개수 N

        // N이 1인 경우 처리: 트리의 지름은 0
        if (N == 1) {
            System.out.println(0);
            return;  // 바로 종료
        }

        node = new ArrayList[N + 1];  // 각 노드에 대한 인접 리스트 배열
        for (int i = 1; i <= N; i++) {
            node[i] = new ArrayList<>();  // 각 노드에 대해 새로운 ArrayList 초기화
        }

        // 트리의 간선 정보를 입력받아 인접 리스트를 구성
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());  // 가중치

            // 양방향 간선 추가
            node[from].add(new int[] { to, w });
            node[to].add(new int[] { from, w });
        }

        // 첫 번째 DFS: 임의의 노드(1번)에서 시작하여 가장 먼 노드를 찾는다.
        visited = new boolean[N + 1];  // 방문 배열 초기화
        dfs(1, 0);

        // 두 번째 DFS: 첫 번째 DFS에서 찾은 가장 먼 노드에서 다시 DFS를 진행하여 지름을 구한다.
        visited = new boolean[N + 1];  // 방문 배열 초기화
        dfs(maxNode, 0);  // 가장 먼 노드에서 다시 DFS

        // 트리의 지름 출력
        System.out.println(maxDist);
    }

    // DFS 함수 (nodeIdx: 현재 노드, dist: 현재까지의 거리)
    private static void dfs(int nodeIdx, int dist) {
        visited[nodeIdx] = true;  // 현재 노드 방문 처리

        // 현재까지의 거리(dist)가 더 크다면, maxDist 갱신
        if (dist > maxDist) {
            maxDist = dist;
            maxNode = nodeIdx;  // 가장 먼 노드 갱신
        }

        // 현재 노드의 인접한 노드들에 대해 DFS 진행
        for (int[] neighbor : node[nodeIdx]) {
            int nextNode = neighbor[0];  // 인접한 노드
            int weight = neighbor[1];    // 간선의 가중치

            // 방문하지 않은 노드만 탐색
            if (!visited[nextNode]) {
                dfs(nextNode, dist + weight);  // 가는 길에 가중치 더해서 탐색
            }
        }
    }
}
