import java.io.*;
import java.util.*;

public class Main {
	private static int N, X;
	private static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		X = Integer.parseInt(br.readLine());
		System.out.println(twoPointer());
	}

	private static int twoPointer() {
		int left = 0;
		int right = N - 1;
		int cnt = 0;

		while (left < right) {
			int sum = nums[left] + nums[right];

			if (sum == X) {
				cnt++;
				right--;
			} else if (sum > X) {
				right--;
			} else {
				left++;
			}
		}
		return cnt;
	}
}