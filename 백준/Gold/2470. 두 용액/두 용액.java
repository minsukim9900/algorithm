import java.io.*;
import java.util.*;

class Main {
	private static int N;
	private static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		StringBuilder sb = new StringBuilder();
		for(int i : simulate()) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int[] simulate() {
		int[] result = null;
		int min = Integer.MAX_VALUE;
		int left = 0;
		int right = N - 1;

		while (left < right) {
			int sum = Math.abs(nums[left] + nums[right]);

			if (sum == 0) {
				return new int[] { nums[left], nums[right] };
			}

			if (min > sum) {
				min = sum;
				result = new int[] { nums[left], nums[right] };
			}
			
			if (Math.abs(nums[left]) > Math.abs(nums[right])) {
				left++;
			} else {
				right--;
			}

		}
		return result;
	}
}