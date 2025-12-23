import java.io.*;
import java.util.*;

public class Main {
	private static int[] nums;
	private static final int N = 6;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int answer = nums[6];
		answer += nums[5];
		nums[1] = Math.max(0, nums[1] - (11 * nums[5]));

		answer += nums[4];
		int need2for4 = Math.min(nums[2], 5 * nums[4]);

		nums[2] -= need2for4;
		nums[1] = Math.max(0, nums[1] - 4 * (5 * nums[4] - need2for4));

		answer += nums[3] / 4;
		int modul = nums[3] % 4;

		if (modul > 0) {
			answer++;
			int need1 = 0;
			int need2 = 0;
			if (modul == 1) {
				need1 = 7;
				need2 = 5;
			} else if (modul == 2) {
				need1 = 6;
				need2 = 3;
			} else {
				need1 = 5;
				need2 = 1;
			}

			int use2 = Math.min(nums[2], need2);
			nums[2] -= use2;

			int lack = need2 - use2;
			nums[1] = Math.max(0, nums[1] - (need1 + 4 * lack));
		}

		answer += (nums[2] / 9);
		modul = nums[2] % 9;

		if (modul > 0) {
			answer++;
			int lack = 9 - modul;
			nums[1] = Math.max(0, nums[1] - 4 * lack);
		}

		if (nums[1] > 0) {
			answer += (nums[1] + 35) / 36;
		}
		System.out.println(answer);
	}
}