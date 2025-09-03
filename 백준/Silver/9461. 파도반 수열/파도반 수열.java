import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		for(int i = 3; i <= 100; i++) {
			dp[i] = dp[i - 2] + dp[i - 3];
		}
		
		for(int t = 0; t < T; t++) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
