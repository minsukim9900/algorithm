import java.io.*;
import java.util.*;

public class Main {
	private static int D, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int[] day = new int[D];
		for (int i = 0; i < D; i++) {
			day[i] = Integer.parseInt(br.readLine());
		}

		int[][] clothes = new int[N][];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int low = Integer.parseInt(st.nextToken());
			int high = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());

			clothes[i] = new int[] { low, high, score };
		}

		int[][] dp = new int[D][N];
		for (int i = 0; i < D; i++) {
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < N; i++) {
			if (clothes[i][0] <= day[0] && day[0] <= clothes[i][1]) {
				dp[0][i] = 0;
			}
		}

		for (int d = 1; d < D; d++) {
			for (int curr = 0; curr < N; curr++) {
				if (clothes[curr][0] <= day[d] && day[d] <= clothes[curr][1]) {

					for (int prev = 0; prev < N; prev++) {
						if (dp[d - 1][prev] == -1)
							continue;

						if (clothes[prev][0] <= day[d - 1] && day[d - 1] <= clothes[prev][1]) {

							dp[d][curr] = Math.max(dp[d][curr],
									dp[d - 1][prev] + Math.abs(clothes[curr][2] - clothes[prev][2]));
						}
					}
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, dp[D - 1][i]);
		}
		System.out.println(answer);
	}
}