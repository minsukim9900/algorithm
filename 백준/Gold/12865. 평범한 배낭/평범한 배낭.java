import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[][] info = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken()); // weight
			info[i][1] = Integer.parseInt(st.nextToken()); // cost
		}

		int[][] dp = new int[N + 1][W + 1];

		for (int i = 1; i <= N; i++) {

			for (int w = 0; w <= W; w++) {

				if (info[i][0] <= w) {
					
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-info[i][0]] + info[i][1]);
					
				} else {	
					dp[i][w] = dp[i - 1][w];
				}

			}
		}
		
		System.out.println(dp[N][W]);
	}
}