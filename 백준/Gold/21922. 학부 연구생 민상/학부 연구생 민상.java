import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        List<int[]> start = new ArrayList<>();
        boolean isPossible = false;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());

                if (board[r][c] == 9) {
                    start.add(new int[]{r, c, -1});
                    isPossible = true;
                }
            }
        }
        if (!isPossible) {
            System.out.println(0);
            return;
        }
        System.out.println(bfs(start));
    }

    private static int bfs(List<int[]> start) {
        boolean[][][] visited = new boolean[4][N][M];
        Queue<int[]> q = new ArrayDeque<>(start);
        int result = 0;
        boolean[][] wind = new boolean[N][M];

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int dir = curr[2];
            wind[r][c] = true;


            if (dir == -1) {
                for (int i = 0; i < 4; i++) {
                    int nr = r + delta[i][0];
                    int nc = c + delta[i][1];

                    if (isRange(nr, nc)) {
                        int nd = i;
                        if (board[nr][nc] > 0 && board[nr][nc] < 5) {
                            switch (board[nr][nc]) {
                                case 1:
                                    nd = itemA(nd);
                                    break;
                                case 2:
                                    nd = itemB(nd);
                                    break;
                                case 3:
                                    nd = itemC(nd);
                                    break;
                                case 4:
                                    nd = itemD(nd);
                                    break;
                            }
                            ;
                        }
                        visited[nd][nr][nc] = true;
                        q.add(new int[]{nr, nc, nd});
                    }
                }
            } else {
                int nr = r + delta[dir][0];
                int nc = c + delta[dir][1];

                if (isRange(nr, nc)) {
                    int nd = dir;
                    if (board[nr][nc] > 0 && board[nr][nc] < 5) {
                        switch (board[nr][nc]) {
                            case 1:
                                nd = itemA(nd);
                                break;
                            case 2:
                                nd = itemB(nd);
                                break;
                            case 3:
                                nd = itemC(nd);
                                break;
                            case 4:
                                nd = itemD(nd);
                                break;
                        }
                    }

                    if (!visited[nd][nr][nc]) {
                        visited[nd][nr][nc] = true;
                        q.add(new int[]{nr, nc, nd});
                    }
                }
            }

        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (wind[r][c]) {
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static int itemA(int dir) {
        switch (dir) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return dir ^ 1;
        }
    }

    private static int itemB(int dir) {
        switch (dir) {
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return dir ^ 1;
        }
    }

    private static int itemC(int dir) {
        switch (dir) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 2:
                return 1;
            default:
                return 0;
        }
    }

    private static int itemD(int dir) {
        switch (dir) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            default:
                return 1;
        }
    }
}