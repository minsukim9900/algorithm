import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[][] board, prefix;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];

        for (int r = 1; r < N + 1; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 1; c < N + 1; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
            }
        }

        prefix = new int[N + 1][N + 1];

        for (int r = 1; r < N + 1; r++) {
            int sum = 0;

            for (int c = 1; c < N + 1; c++) {
                sum += board[r][c];

                prefix[r][c] = prefix[r - 1][c] + sum;
            }
        }
    }

    private static int cal() {
        int answer = 0;

        for (int sr = 1; sr < N + 1 - (K - 1); sr++) {
            for (int sc = 1; sc < N + 1 - (K - 1); sc++) {
                int er = sr + K - 1;
                int ec = sc + K - 1;
                int sum = prefix[er][ec] - prefix[sr - 1][ec] - prefix[er][sc - 1] + prefix[sr - 1][sc - 1];

                answer = Math.max(answer, sum);
            }
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}