import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, x, y;
	private static int[] doors;
	private static int[][][] dp;
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		doors = new int[M];
		for (int i = 0; i < M; i++) {
			doors[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[N + 1][N + 1][M];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Arrays.fill(dp[i][j], INF);
			}
		}

		System.out.println(solve(x, y, 0));
	}

	private static int solve(int left, int right, int depth) {
		if (depth == M) {
			return 0;
		}

		if (left > right) {
			int tmp = left;
			left = right;
			right = tmp;
		}

		if (dp[left][right][depth] != INF)
			return dp[left][right][depth];

		int leftCost = Math.abs(left - doors[depth]) + solve(doors[depth], right, depth + 1);
		int rightCost = Math.abs(right - doors[depth]) + solve(left, doors[depth], depth + 1);

		return dp[left][right][depth] = Math.min(leftCost, rightCost);
	}
}