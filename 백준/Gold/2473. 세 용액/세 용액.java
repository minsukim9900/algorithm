import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long[] nums, result;
	private static long min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		min = Long.MAX_VALUE;

		nums = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		for (int i = 0; i < N - 2; i++) {

			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				long sum = nums[left] + nums[right] + nums[i];
				if (Math.abs(min) > Math.abs(sum)) {
					min = sum;
					result = new long[] { nums[i], nums[left], nums[right] };
				}

				if (sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}
		for (long w : result) {
			sb.append(w).append(" ");
		}
		System.out.println(sb.toString());
	}
}