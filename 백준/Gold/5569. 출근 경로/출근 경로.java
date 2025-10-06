import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][][][] dp = new int[H + 1][W + 1][2][2];
		int mod = 100_000;

		for (int w = 1; w <= W; w++) {
			dp[1][w][0][0] = 1;
		}

		for (int h = 1; h <= H; h++) {
			dp[h][1][1][0] = 1;
		}

		for (int r = 2; r <= H; r++) {
			for (int c = 2; c <= W; c++) {
				dp[r][c][0][0] = (dp[r][c - 1][0][0] + dp[r][c - 1][0][1]) % mod;
				dp[r][c][1][0] = (dp[r - 1][c][1][0] + dp[r - 1][c][1][1]) % mod;
				dp[r][c][0][1] = dp[r][c - 1][1][0] % mod;
				dp[r][c][1][1] = dp[r - 1][c][0][0] % mod;
			}
		}

		System.out.println((dp[H][W][0][0] + dp[H][W][0][1] + dp[H][W][1][0] + dp[H][W][1][1]) % mod);
	}
}
