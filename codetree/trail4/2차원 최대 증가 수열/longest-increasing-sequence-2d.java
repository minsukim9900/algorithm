import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board, dp;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dp = new int[N][M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < M; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
            }
        }
    }

    private static int cal() {
        if(board[0][0] > 0) {
            dp[0][0] = 1;
        }

        int result = 0;

        for(int tr = 0; tr < N; tr++) {
            for(int tc = 0; tc < M; tc++) {
                if(tr == 0 && tc == 0) {
                    continue;
                }

                int num = board[tr][tc];

                for(int r = 0; r < tr; r++) {
                    for(int c = 0; c < M; c++) {
                        if(c >= tc) {
                            break;
                        }

                        if(dp[r][c] == 0 || board[r][c] >= num) {
                            continue;
                        }

                        dp[tr][tc] = Math.max(dp[tr][tc], dp[r][c] + 1);
                    }
                }

                result = Math.max(result, dp[tr][tc]);
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}