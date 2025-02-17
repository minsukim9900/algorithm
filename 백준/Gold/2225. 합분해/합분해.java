import java.io.*;
import java.util.*;

public class Main {

	private static int N, K;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][K + 1];
		for (int i = 1; i <= K; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i <= N; i++) {
			dp[i][1] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = ((dp[i][j - 1] + dp[i - 1][j])) % 1000000000;
			}
		}
		
		System.out.println(dp[N][K]);

	}

}
