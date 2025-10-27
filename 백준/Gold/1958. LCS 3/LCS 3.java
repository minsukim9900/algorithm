import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str1 = br.readLine();
		String str2 = br.readLine();
		String str3 = br.readLine();
		System.out.println(solution(str1, str2, str3));
	}

	private static int solution(String str1, String str2, String str3) {
		int N = str1.length() + 1;
		int M = str2.length() + 1;
		int K = str3.length() + 1;

		int[][][] dp = new int[N][M][K];
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				for (int k = 1; k < K; k++) {
					if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					} else {
						dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
					}
				}
			}
		}

		return dp[N - 1][M - 1][K - 1];
	}
}
