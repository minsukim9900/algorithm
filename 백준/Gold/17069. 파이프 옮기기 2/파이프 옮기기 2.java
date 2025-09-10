import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] board;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		board = new int[N + 1][N + 1];

		long[][][] dp = new long[N + 1][N + 1][3];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][2][0] = 1L;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r == 1 && c == 2)
					continue;
				if (board[r][c] == 1)
					continue;
				dp[r][c][0] += (dp[r][c - 1][0] + dp[r][c - 1][1]);
				dp[r][c][2] += (dp[r - 1][c][2] + dp[r - 1][c][1]);

				if (board[r][c] == 1 || board[r - 1][c] == 1 || board[r][c - 1] == 1)
					continue;
				dp[r][c][1] += (dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2]);
			}
		}

		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);

	}

}