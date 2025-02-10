import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] dp;
	private static int[][] delta = { { 1, 0 }, { 1, 1 } };
	private static int[][] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N + 2][N + 2];
		dp = new int[N+2][N+2];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				if (c > r)
					break;
				nums[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][1] = nums[1][1];

		for (int r = 2; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				for (int i = 0; i < 2; i++) {
					dp[r][c] = Math.max(dp[r][c], nums[r][c] + dp[r-delta[i][0]][c-delta[i][1]]);
				}
			}
		}
		
		Arrays.sort(dp[N]);
		System.out.println(dp[N][dp[N].length - 1]);

	}

}
