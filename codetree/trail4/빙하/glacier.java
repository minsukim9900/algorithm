import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, total;
    private static int[][] board;
    private static int[][] delta = {{-1 ,0}, {1, 0}, {0, -1}, {0, 1}};

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        total = 0;

        board = new int[N][M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < M; c++) {
                int num = Integer.parseInt(st.nextToken());

                if(num == 1) {
                    total++;
                }

                board[r][c] = num;
            }
        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static int[] simulate() {

        int time = 0;
        int result = 0;

        while(total > 0) {
            time++;
            int count = 0;

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[] {0, 0, 0});

            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;

            while(!q.isEmpty()) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for(int i = 0; i < 4; i++) {
                    int nr = r + delta[i][0];
                    int nc = c + delta[i][1];

                    if(isRange(nr, nc) && !visited[nr][nc]) {
                        visited[nr][nc] = true;

                        if(board[nr][nc] == 1) {
                            count++;
                        }

                        if(board[nr][nc] == 0) {
                            q.add(new int[] {nr, nc});
                        }

                        board[nr][nc] = 0;
                    }
                }
            }   
            result = count;
            total -= count;
        }

        return new int[] {time, result};
    }

    public static void main(String[] args) throws Exception {
        init();

        int[] answer = simulate();

        System.out.println(answer[0] + " " + answer[1]);
    }
}