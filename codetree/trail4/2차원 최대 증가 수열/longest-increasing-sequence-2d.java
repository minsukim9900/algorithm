import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] board;

    private static class Cell {
        int r;
        int c;
        int value;

        Cell(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }

    private static class SegmentTree2D {
        int n;
        int m;
        int[][] tree;

        SegmentTree2D(int n, int m) {
            this.n = n;
            this.m = m;
            this.tree = new int[n * 2][m * 2];
        }

        void update(int r, int c, int value) {
            for (int row = r + n; row > 0; row >>= 1) {
                for (int col = c + m; col > 0; col >>= 1) {
                    tree[row][col] = Math.max(tree[row][col], value);
                }
            }
        }

        int query(int r1, int c1, int r2, int c2) {
            if (r1 > r2 || c1 > c2) {
                return 0;
            }

            int result = 0;

            int rowLeft = r1 + n;
            int rowRight = r2 + n + 1;

            while (rowLeft < rowRight) {
                if ((rowLeft & 1) == 1) {
                    result = Math.max(result, queryCol(rowLeft, c1, c2));
                    rowLeft++;
                }

                if ((rowRight & 1) == 1) {
                    rowRight--;
                    result = Math.max(result, queryCol(rowRight, c1, c2));
                }

                rowLeft >>= 1;
                rowRight >>= 1;
            }

            return result;
        }

        private int queryCol(int row, int c1, int c2) {
            int result = 0;

            int colLeft = c1 + m;
            int colRight = c2 + m + 1;

            while (colLeft < colRight) {
                if ((colLeft & 1) == 1) {
                    result = Math.max(result, tree[row][colLeft]);
                    colLeft++;
                }

                if ((colRight & 1) == 1) {
                    colRight--;
                    result = Math.max(result, tree[row][colRight]);
                }

                colLeft >>= 1;
                colRight >>= 1;
            }

            return result;
        }
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int cal() {
        Cell[] cells = new Cell[N * M];

        int index = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                cells[index++] = new Cell(r, c, board[r][c]);
            }
        }

        Arrays.sort(cells, (a, b) -> Integer.compare(a.value, b.value));

        SegmentTree2D segTree = new SegmentTree2D(N, M);

        int[] tempDp = new int[N * M];

        int answer = 0;
        int start = 0;

        while (start < cells.length) {
            int end = start;
            int value = cells[start].value;

            while (end < cells.length && cells[end].value == value) {
                end++;
            }

            for (int i = start; i < end; i++) {
                Cell cur = cells[i];

                if (cur.r == 0 && cur.c == 0) {
                    tempDp[i] = 1;
                } else {
                    int best = segTree.query(0, 0, cur.r - 1, cur.c - 1);

                    if (best > 0) {
                        tempDp[i] = best + 1;
                    }
                }

                answer = Math.max(answer, tempDp[i]);
            }

            for (int i = start; i < end; i++) {
                Cell cur = cells[i];

                if (tempDp[i] > 0) {
                    segTree.update(cur.r, cur.c, tempDp[i]);
                }
            }

            start = end;
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}