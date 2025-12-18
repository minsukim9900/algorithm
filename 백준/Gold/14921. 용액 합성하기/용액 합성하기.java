import java.io.*;
import java.util.*;

public class Main {
	private static final long ZERO = 0L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		long min = Long.MAX_VALUE;
		long answer = 0;

		while (left != right) {
			long sum = nums[left] + nums[right];
			long diff = Math.abs(ZERO - sum);
			if (min > diff) {
				min = diff;
				answer = sum;
			}

			if (sum > 0) {
				right--;
			} else if (sum == 0) {
				break;
			} else {
				left++;
			}
		}
		System.out.println(answer);
	}
}