import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int[][] board = new int[N][3];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] maxDP = new int[N][3];
		int[][] minDP = new int[N][3];

		for (int i = 0; i < N; i++) {
			Arrays.fill(minDP[i], Integer.MAX_VALUE);
		}

		maxDP[0][0] = board[0][0];
		maxDP[0][1] = board[0][1];
		maxDP[0][2] = board[0][2];
		minDP[0][0] = board[0][0];
		minDP[0][1] = board[0][1];
		minDP[0][2] = board[0][2];

		for (int r = 1; r < N; r++) {
			maxDP[r][0] = Math.max(maxDP[r - 1][0], maxDP[r - 1][1]) + board[r][0];
			maxDP[r][1] = Math.max(maxDP[r - 1][0], Math.max(maxDP[r - 1][1], maxDP[r - 1][2])) + board[r][1];
			maxDP[r][2] = Math.max(maxDP[r - 1][1], maxDP[r - 1][2]) + board[r][2];

			minDP[r][0] = Math.min(minDP[r - 1][0], minDP[r - 1][1]) + board[r][0];
			minDP[r][1] = Math.min(minDP[r - 1][0], Math.min(minDP[r - 1][1], minDP[r - 1][2])) + board[r][1];
			minDP[r][2] = Math.min(minDP[r - 1][1], minDP[r - 1][2]) + board[r][2];
		}

		int max = 0;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < 3; i++) {
			max = Math.max(maxDP[N - 1][i], max);
			min = Math.min(minDP[N - 1][i], min);
		}
		sb.append(max).append(" ").append(min);
		System.out.println(sb.toString());
	}
}