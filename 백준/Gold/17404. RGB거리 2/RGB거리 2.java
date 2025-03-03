import java.io.*;
import java.util.*;

public class Main {

	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			rgb[i][0] = Integer.parseInt(st.nextToken());
			rgb[i][1] = Integer.parseInt(st.nextToken());
			rgb[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = null;
		int ans = INF;
		for (int i = 0; i < 3; i++) {

			dp = new int[N + 1][3];
			Arrays.fill(dp[1], INF);
			dp[1][i] = rgb[1][i];

			for (int j = 2; j <= N; j++) {
				dp[j][0] = Math.min(dp[j - 1][1], dp[j-1][2]) + rgb[j][0];
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + rgb[j][1];
				dp[j][2] = Math.min(dp[j - 1][1], dp[j - 1][0])+ rgb[j][2];
				
			}
			
			ans = Math.min(ans, Math.min(dp[N][(i + 1) %3], dp[N][(i+2) %3]));

		}
		
		System.out.println(ans);

	}
}
