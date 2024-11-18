import java.io.*;
import java.util.*;

public class Main {

	private static int R, C, N;
	private static int[][] board;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static Queue<int[]> blasting = new ArrayDeque<>();
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[R][C];

		for (int r = 0; r < R; r++) {
			String str = br.readLine();

			for (int c = 0; c < C; c++) {
				char tmp = str.charAt(c);
				if (tmp == '.') {
					board[r][c] = 0;
				} else {
					board[r][c] = 1;
				}

			}

		}
		int n = 1;
		while (n <= N && N != 1) {
			act1();
			n++;
			if (n >= N) {
				break;
			}
			act2_1();
			n++;
			if (n >= N) {
				break;
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(board[r][c] == 0) {
					sb.append(".");
				}
				else {
					sb.append("O");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

	private static void act1() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				board[r][c]++;
			}
		}
		
		
	}

	private static void act2_1() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				board[r][c]++;
				if (board[r][c] >= 3) {
					board[r][c] = 0;
					blasting.add(new int[] { r, c });
				}
			}
		}
		while (!blasting.isEmpty()) {
			int[] curr = blasting.poll();
			act2_2(curr[0], curr[1]);
		}
		
		
	}

	private static void act2_2(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
				board[nr][nc] = 0;
			}
		}
		
		
		
		
	}

}
