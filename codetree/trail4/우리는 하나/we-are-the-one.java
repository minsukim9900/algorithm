import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, U, D, index, answer;
    private static int[][] board, indexBoard;
    private static Map<Integer, Node> map;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static class Node {
        private int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Math.min(N, Integer.parseInt(st.nextToken()));
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        index = 0;
        answer = 0;

        board = new int[N][N];
        indexBoard = new int[N][N];

        map = new HashMap<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
                indexBoard[r][c] = index;
                map.put(index++, new Node(r, c));
            }
        }
    }

    private static boolean isRange(int nr, int nc, int r, int c) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N && Math.abs(board[nr][nc] - board[r][c]) >= U && Math.abs(board[nr][nc] - board[r][c]) <= D;
    }

    private static int bfs(int[] arr) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < arr.length; i++) {
            Node node = map.get(arr[i]);

            int r = node.r;
            int c = node.c;

            visited[r][c] = true;
            q.add(new int[] {r, c});
        }

        int result = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int value = board[r][c];

            result++;

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc, r, c) && !visited[nr][nc]) {
                    q.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        return result;
    }

    private static void dfs(int depth, int[] arr, boolean[] visited) {
        if (depth == K) {
            int result = bfs(arr);
            answer = Math.max(answer, result);

            return;
        }

        for (int i = 0; i < index; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            arr[depth] = i;
            dfs(depth + 1, arr, visited);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, new int[K], new boolean[index]);
        System.out.println(answer);
    }
}