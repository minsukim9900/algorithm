class Solution {
    private int N, M;

    public int[] solution(int rows, int columns, int[][] queries) {
        N = rows;
        M = columns;

        int[] answer = new int[queries.length];
        int[][] board = new int[N][M];

        int num = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                board[r][c] = num++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            int sr = query[0] - 1;
            int sc = query[1] - 1;
            int er = query[2] - 1;
            int ec = query[3] - 1;

            answer[i] = rotate(board, sr, sc, er, ec);
        }

        return answer;
    }

    private int rotate(int[][] board, int sr, int sc, int er, int ec) {
        int prev = board[sr][sc];
        int minNum = prev;

        for (int c = sc + 1; c <= ec; c++) {
            int temp = board[sr][c];

            board[sr][c] = prev;
            prev = temp;

            minNum = Math.min(minNum, prev);
        }

        for (int r = sr + 1; r <= er; r++) {
            int temp = board[r][ec];

            board[r][ec] = prev;
            prev = temp;

            minNum = Math.min(minNum, prev);
        }

        for (int c = ec - 1; c >= sc; c--) {
            int temp = board[er][c];

            board[er][c] = prev;
            prev = temp;

            minNum = Math.min(minNum, prev);
        }

        for (int r = er - 1; r >= sr; r--) {
            int temp = board[r][sc];

            board[r][sc] = prev;
            prev = temp;

            minNum = Math.min(minNum, prev);
        }

        return minNum;
    }
}