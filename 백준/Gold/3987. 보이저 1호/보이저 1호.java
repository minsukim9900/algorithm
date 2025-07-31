import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static char[][] board;
    // 0: U, 1: R, 2: D, 3: L
    private static int[][] delta = {
        { -1, 0 },  // U
        {  0, 1 },  // R
        {  1, 0 },  // D
        {  0,-1 }   // L
    };
    private static char[] dirChar = { 'U', 'R', 'D', 'L' };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int r = 0; r < N; r++) {
            board[r] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;

        int bestDir = 0, maxTime = -1;
        // 첫 무한 방향을 기록할 변수. 없다면 -1
        int infiniteDir = -1;

        for (int d = 0; d < 4; d++) {
            int t = simulate(sr, sc, d);
            if (t == -1) {
                // 무한 전파 최초 발견
                infiniteDir = d;
                break;
            } else if (t > maxTime) {
                maxTime = t;
                bestDir = d;
            }
        }

        if (infiniteDir != -1) {
            System.out.println(dirChar[infiniteDir]);
            System.out.println("Voyager");
        } else {
            System.out.println(dirChar[bestDir]);
            System.out.println(maxTime);
        }
    }

    /**
     * (r, c)에서 initialDir 방향으로 전파를 시작했을 때,
     * 무한루프 발생 시 -1을 리턴, 아니면 소요된 시간(이동한 칸 수)을 리턴.
     */
    private static int simulate(int r, int c, int initialDir) {
        boolean[][][] visited = new boolean[N][M][4];
        int cr = r, cc = c, dir = initialDir, time = 0;

        while (true) {
            // 한 칸 이동
            int nr = cr + delta[dir][0];
            int nc = cc + delta[dir][1];
            time++;

            // 영역 밖이거나 블랙홀(C)이면 종료
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 'C') {
                break;
            }

            // 이미 같은 위치·방향으로 왔었다면 무한 전파
            if (visited[nr][nc][dir]) {
                return -1;
            }
            visited[nr][nc][dir] = true;

            // 거울에 맞으면 방향 반사
            char cell = board[nr][nc];
            if (cell == '/') {
                dir ^= 1;       // U↔R, D↔L
            } else if (cell == '\\') {
                dir = 3 - dir;  // U↔L, R↔D
            }

            cr = nr;
            cc = nc;
        }

        return time;
    }
}