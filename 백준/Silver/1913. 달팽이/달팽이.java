import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[][] board;
	private static int saveNum;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		saveNum = Integer.parseInt(br.readLine());
		board = new int[N][N];
		simulate();
		System.out.println(sb.toString());
	}

	private static void simulate() {
		int num = N * N;
		int size = N;
		int r = 0;
		int c = 0;
		int dir = 1;
		while (num > 0) {

			for (int i = 0; i < size; i++) {
				board[r][c] = num--;
				r += dir;
			}

			r -= dir;
			c += dir;
			size--;
			for (int i = 0; i < size; i++) {
				board[r][c] = num--;
				c += (dir);
			}

			c -= dir;
			dir *= -1;
			r += dir;
		}
		int[] ans = new int[2];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				sb.append(board[y][x] + " ");
				if (board[y][x] == saveNum) {
					ans[0] = y + 1;
					ans[1] = x + 1;
				}
			}
			sb.append("\n");
		}

		for (int w : ans) {
			sb.append(w).append(" ");
		}

	}

}