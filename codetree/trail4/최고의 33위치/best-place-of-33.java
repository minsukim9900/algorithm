import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;
    private static int[][] prefix;

    private static int cal() {
        int result = 0;

        for (int er = 3; er < N + 1; er++) {
            for (int ec = 3; ec < N + 1; ec++) {
                int sr = er - 3 + 1;
                int sc = ec - 3 + 1;

                int count = prefix[er][ec] - prefix[er][sc - 1] - prefix[sr - 1][ec] + prefix[sr - 1][sc - 1];

                result = Math.max(result, count);
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

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

        System.out.println(cal());
    }
}