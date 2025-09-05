import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N + 1][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			info[i][0] = time;
			info[i][1] = pay;
		}

		int[] dp = new int[N + 6];
		for (int i = 0; i < N; i++) {
			int time = info[i][0];
			int pay = info[i][1];

			dp[time + i] = Math.max(dp[time + i], dp[i] + pay);
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
		}

		System.out.println(dp[N]);
	}
}
