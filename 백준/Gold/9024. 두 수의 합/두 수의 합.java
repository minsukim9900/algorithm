import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			nums = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(nums);
			sb.append(cal()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int cal() {
		int result = 0;

		int left = 0;
		int right = N - 1;
		int minDiff = Integer.MAX_VALUE;

		while (left < right) {
			int sum = nums[left] + nums[right];
			int diff = Math.abs(sum - K);

			if (diff < minDiff) {
				result = 1;
				minDiff = diff;
			} else if (diff == minDiff) {
				result++;
			}

			if (sum > K) {
				right--;
			} else if (sum == K) {
				left++;
				right--;
			} else {
				left++;
			}
		}

		return result;
	}

}