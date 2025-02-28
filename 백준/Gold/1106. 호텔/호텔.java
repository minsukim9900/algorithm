import java.io.*;
import java.util.*;

public class Main {

	private static int C, N;
	private static int[][] info;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		info = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[C + 101];

		Arrays.fill(dp, 987654321);
		dp[0] = 0;

		for (int i = 0; i < N; i++) {

			for (int j = info[i][1]; j < dp.length; j++) {

				dp[j] = Math.min(dp[j], dp[j - info[i][1]] + info[i][0]);
				
			}

		}
		

		int result = Integer.MAX_VALUE;
		for (int i = C; i < dp.length; i++) {
			result = Math.min(dp[i], result);
		}

		System.out.println(result);

	}
}
