import java.util.*;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int count = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (K == 0) {
				break;
			}

			if (nums[i] <= K) {
				count += (K / nums[i]);
				K -= (K / nums[i] * nums[i]);
			}
		}
		System.out.println(count);
	}
}