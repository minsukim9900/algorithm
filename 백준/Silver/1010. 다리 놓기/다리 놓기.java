import java.io.*;
import java.util.*;

public class Main {
	private static int dp[][];
	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			dp = new int[M + 1][N + 1];
			
			for(int i = 0; i<=M; i++) {
				dp[i][0] = 1;
			}
			
			for (int i = 1; i <= M; i++) {
				for (int j = 1; j <= N; j++) {
					if(j > i) break;
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}
			System.out.println(dp[M][N]);
		}

	}
}