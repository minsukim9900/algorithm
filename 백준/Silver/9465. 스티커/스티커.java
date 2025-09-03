import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[2][N + 2];

			int[][] nums = new int[2][N + 2];
			for (int r = 0; r <= 1; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 2; c <= N + 1; c++) {
					nums[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 2; i <= N + 1; i++) {
				dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + nums[0][i];
				dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + nums[1][i];
			}
			
			sb.append(Math.max(dp[0][N + 1], dp[1][N + 1])).append("\n");
		}
		System.out.println(sb.toString());
	}
}
