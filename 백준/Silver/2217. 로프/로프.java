import java.util.*;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nums);

		int max = nums[N - 1];
		int count = 2;
		for (int i = N - 2; i >= 0; i--) {
			max = Math.max(max, nums[i] * count++);
		}
		System.out.println(max);
	}
}