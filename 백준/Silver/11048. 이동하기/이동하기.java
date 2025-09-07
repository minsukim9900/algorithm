import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N, M;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				int num = Integer.parseInt(st.nextToken());
				dp[r][c] = Math.max(dp[r - 1][c], Math.max(dp[r][c - 1], dp[r - 1][c - 1])) + num;
			}
		}

		System.out.println(dp[N][M]);
	}
}
