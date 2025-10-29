import java.io.*;
import java.util.*;

public class Main {
	private static long[][] dp = new long[31][31];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		dp[1][0] = 1;
		dp[2][0] = 2;
		dp[3][0] = 5;
		
		cal(30, 0);
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			sb.append(dp[N][0]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static long cal(int one, int half) {
		if (one == 0) {
			return 1;
		}

		if (dp[one][half] != 0) {
			return dp[one][half];
		}

		long cnt = 0;

		if (one != 0) {
			cnt += cal(one - 1, half + 1);
		}

		if (half != 0) {
			cnt += cal(one, half - 1);
		}

		return dp[one][half] = cnt;
	}
}