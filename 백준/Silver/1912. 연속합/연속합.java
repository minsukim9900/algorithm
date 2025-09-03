import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		int max = -1_001;
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(dp[i - 1] + num, num);
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
