import java.io.*;
import java.util.*;;

public class Main {
	private static int N, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int[] dp = new int[T + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int time = T; time >= x; time--) {
				dp[time] = Math.max(dp[time - x] + y, dp[time]);
			}
		}
		System.out.println(dp[T]);
	}
}
