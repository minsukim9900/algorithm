import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str1 = br.readLine();
		String str2 = br.readLine();
		int[][] dp = getDP(str1, str2);
		System.out.println(getLCSToString(str1, str1.length(), str2.length(), dp));
	}

	private static String getLCSToString(String str, int i, int j, int[][] dp) {
		Stack<Character> stack = new Stack<>();

		while (i > 0 && j > 0) {
			if (i == 0 || j == 0) {
				break;
			}

			if (dp[i][j] == dp[i - 1][j]) {
				i--;
			} else if (dp[i][j] == dp[i][j - 1]) {
				j--;
			} else {
				stack.push(str.charAt(i - 1));
				i--;
				j--;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}

	private static int[][] getDP(String str1, String str2) {
		int N = str1.length() + 1;
		int M = str2.length() + 1;
		int[][] dp = new int[N][M];

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp;
	}
}
