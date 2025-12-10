import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		int left = 0;
		int right = N - 1;
		int answer = 0;

		while (left < right) {
			int sum = nums[left] + nums[right];

			if (sum == M) {
				answer++;
				left++;
				right--;
			} else if (sum > M) {
				right--;
			} else {
				left++;
			}
		}
		System.out.println(answer);
	}
}