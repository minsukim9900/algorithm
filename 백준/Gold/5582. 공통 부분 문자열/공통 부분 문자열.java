import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str1 = br.readLine();
		String str2 = br.readLine();

		System.out.println(solution(str1, str2));
	}

	private static int solution(String str1, String str2) {
		int N = str1.length() + 1;
		int M = str2.length() + 1;

		int[][] dp = new int[N][M];
		int max = 0;

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}
}
