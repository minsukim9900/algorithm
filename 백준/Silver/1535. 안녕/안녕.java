import java.io.*;
import java.util.*;

public class Main {
	private static final int MAX = 101;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] weight = new int[N];
		int[] price = new int[N];
		int[] dp = new int[MAX];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = MAX - 1; j >= weight[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - weight[i]] + price[i]);
			}
		}
		
		System.out.println(dp[99]);
	}
}