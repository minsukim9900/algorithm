import java.io.*;
import java.util.*;

public class Main {
    private static int N, startNode, total;
    private static int[][] board, nodeBoard;

    private static int[] parent, size;
    private static List<int[]>[] edges;

    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static final int INF = 1_000_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        nodeBoard = new int[N][N];

        total = 1;
        startNode = 1;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                int state = Integer.parseInt(st.nextToken());

                board[r][c] = state;

                if (state > 0) {
                    nodeBoard[r][c] = total++;

                    if(state == 1) {
                        startNode = total - 1;
                    }
                }
            }
        }

        edges = new ArrayList[total];
        parent = new int[total];
        size = new int[total];

        for (int i = 1; i < total; i++) {
            edges[i] = new ArrayList<>();
            parent[i] = i;
            size[i] = 1;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (nodeBoard[r][c] == 0) {
                    continue;
                }

                bfs(r , c, nodeBoard[r][c]);
            }
        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void bfs(int sr, int sc, int num) {
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[sr][sc] = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc});

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc) && board[nr][nc] != -1 && dist[nr][nc] > dist[r][c] + 1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[] {nr, nc});
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if (dist[r][c] == INF || nodeBoard[r][c] == 0 || dist[r][c] == 0) {
                    continue;
                }
                edges[num].add(new int[] {nodeBoard[r][c], num, dist[r][c]});
            }
        }
    }

    private static int solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.addAll(edges[startNode]);

        int pick = 0;
        int answer = 0;

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();

            int nodeX = curr[0];
            int nodeY = curr[1];
            int weight = curr[2];

            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);

            if(rootX != rootY) {
                union(rootX, rootY);
                pick++;
                answer += weight;

                pq.addAll(edges[nodeX]);

                if(pick == total - 1) {
                    break;
                }
            }
        }

        return pick < total - 2 ? -1 : answer;
    }

    private static int findParent(int node) {
        if(node == parent[node]) {
            return node;
        }

        return parent[node] = findParent(parent[node]);
    }

    private static void union(int rootX, int rootY) {

        if(size[rootX] >= size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(solution());
    }
}