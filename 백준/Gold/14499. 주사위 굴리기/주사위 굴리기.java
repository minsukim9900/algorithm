import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, x, y, K;
	private static int[][] board;
	private static int[][] delta = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	private static int[] info = new int[7];
	private static int[] dice = { 1, 2, 3, 4, 5, 6 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int pre = 6;
		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			
			if(isRange(x + delta[dir][0], y + delta[dir][1])) {
				changeBottom(pre, dir);
				pre = dice[5];
				simulate(pre, dir);
			}
		}

	}

	private static void simulate(int buttom, int dir) {
		int nx = x + delta[dir][0];
		int ny = y + delta[dir][1];

		if (isRange(nx, ny)) {

			if (board[nx][ny] == 0) {
				board[nx][ny] = info[buttom];
			} else {
				info[buttom] = board[nx][ny];
				board[nx][ny] = 0;
			}
			x = nx;
			y = ny;
			System.out.println(info[7 - buttom]);
		}
	}

	private static void changeBottom(int pre, int dir) {
		int tmp = dice[5]; // 밑면
		
		if (dir == 1) { // 동
			dice[5] = dice[2];
			dice[2] = dice[0];
			dice[0] = dice[3];
			dice[3] = tmp;
		} else if (dir == 2) { // 서
			dice[5] = dice[3];
			dice[3] = dice[0];
			dice[0] = dice[2];
			dice[2] = tmp;
		} else if (dir == 3) { // 북
			dice[5] = dice[4];
			dice[4] = dice[0];
			dice[0] = dice[1];
			dice[1] = tmp;
		}else { // 남
			dice[5] = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[4];
			dice[4] = tmp;
		}
	}
    
    private static boolean isRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}

}