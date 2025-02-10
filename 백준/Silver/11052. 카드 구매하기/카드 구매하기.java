import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] dp;
	private static int[] cradit;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		cradit = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cradit[i] = Integer.parseInt(st.nextToken());
		}

		dp[1] = cradit[1];

		for (int i = 2; i < N + 1; i++) {
			dp[i] = cradit[i];
			
			int left = 1;
			int right = i-1;
			while(left <= right) {
				dp[i] = Math.max(dp[i], dp[left] +dp[right]);
				left++;
				right--;
			}
			
		}
		
		System.out.println(dp[N]);
		

	}

}
