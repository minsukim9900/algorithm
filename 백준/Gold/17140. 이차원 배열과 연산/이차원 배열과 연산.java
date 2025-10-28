import java.io.*;
import java.util.*;

public class Main {
    private static int R, C, K;
    private static int[][] board;
    private static int maxR, maxC;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[101][101];
        maxR = 3;
        maxC = 3;

        for (int r = 1; r <= 3; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= 3; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t <= 100; t++) {
            if (R <= maxR && C <= maxC && board[R][C] == K) {
                System.out.println(t);
                return;
            }
            if (t == 100) break;

            if (maxR >= maxC) changeRow();
            else changeCol();
        }
        System.out.println(-1);
    }

    private static void changeRow() {
        int newC = 0;
        for (int i = 1; i <= maxR; i++) {
            int[] count = new int[101];
            for (int j = 1; j <= maxC; j++) {
                int v = board[i][j];
                if (v > 0) count[v]++;
            }

            List<int[]> pairs = new ArrayList<>();
            for (int num = 1; num <= 100; num++) {
                if (count[num] > 0) pairs.add(new int[]{num, count[num]});
            }

            pairs.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

            int idx = 1;
            for (int[] p : pairs) {
                if (idx > 100) break;
                board[i][idx++] = p[0];
                if (idx > 100) break;
                board[i][idx++] = p[1];
            }
            for (int j = idx; j <= 100; j++) board[i][j] = 0;
            newC = Math.max(newC, idx - 1);
        }
        maxC = Math.min(newC, 100);
    }

    private static void changeCol() {
        int newR = 0;
        for (int j = 1; j <= maxC; j++) {
            int[] count = new int[101];
            for (int i = 1; i <= maxR; i++) {
                int v = board[i][j];
                if (v > 0) count[v]++;
            }

            List<int[]> pairs = new ArrayList<>();
            for (int num = 1; num <= 100; num++) {
                if (count[num] > 0) pairs.add(new int[]{num, count[num]});
            }

            pairs.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

            int idx = 1;
            for (int[] p : pairs) {
                if (idx > 100) break;
                board[idx++][j] = p[0];
                if (idx > 100) break;
                board[idx++][j] = p[1];
            }
            for (int i = idx; i <= 100; i++) board[i][j] = 0;
            newR = Math.max(newR, idx - 1);
        }
        maxR = Math.min(newR, 100);
    }
}