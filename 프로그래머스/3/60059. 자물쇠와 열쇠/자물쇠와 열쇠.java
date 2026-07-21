class Solution {
    private static int N, M, K;
    private static int[][][] keys;
    private static int[][] locks;

    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        
        K = N + 2 * (M - 1);

        keys = new int[4][M][M];

        int holeCount = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (lock[r][c] == 0) {
                    holeCount++;
                }
            }
        }
        
        locks = new int[K][K];

        for (int r = 0; r < K; r++) {
            for (int c = 0; c < K; c++) {
                locks[r][c] = -1;

                if (lockRange(r, c)) {
                    locks[r][c] = lock[r - (M - 1)][c - (M - 1)];
                }
            }
        }

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                keys[0][r][c] = key[r][c];
                keys[1][M - 1 - c][r] = key[r][c];
                keys[2][M - 1 - r][M - 1 - c] = key[r][c];
                keys[3][c][M - 1 - r] = key[r][c];
            }
        }

        for (int rotation = 0; rotation < 4; rotation++) {
            int[][] currentKey = keys[rotation];

            for (int r = 0; r <= K - M; r++) {
                for (int c = 0; c <= K - M; c++) {
                    int filledHoleCount = 0;
                    boolean valid = true;

                    for (int keyRow = 0; keyRow < M && valid; keyRow++) {
                        for (int keyCol = 0; keyCol < M; keyCol++) {
                            int lockRow = r + keyRow;
                            int lockCol = c + keyCol;

                            if (locks[lockRow][lockCol] == -1) {
                                continue;
                            }

                            if (locks[lockRow][lockCol] == 1
                                    && currentKey[keyRow][keyCol] == 1) {
                                valid = false;
                                break;
                            }

                            if (locks[lockRow][lockCol] == 0
                                    && currentKey[keyRow][keyCol] == 1) {
                                filledHoleCount++;
                            }
                        }
                    }

                    if (valid && filledHoleCount == holeCount) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean lockRange(int r, int c) {
        return r >= M - 1
                && r <= M + N - 2
                && c >= M - 1
                && c <= M + N - 2;
    }
}