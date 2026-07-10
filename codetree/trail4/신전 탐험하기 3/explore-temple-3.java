import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int cal() {
        int[][] dp = new int[N][M];

        for (int c = 0; c < M; c++) {
            dp[0][c] = board[0][c];
        }

        int answer = 0;

        for (int r = 1; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int k = 0; k < M; k++) {
                    if (c == k) {
                        continue;
                    }

                    dp[r][c] = Math.max(dp[r][c], dp[r - 1][k] + board[r][c]);
                }

                answer = Math.max(answer, dp[r][c]);
            }
        }

        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}