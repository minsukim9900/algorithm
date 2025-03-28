import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);
		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			int ans = binarySearch(nums, target);

			sb.append(target == ans ? 1 + " " : 0 + " ");
		}
		System.out.println(sb.toString());
	}

	private static int binarySearch(int[] nums, int target) {
		int s = 0;
		int e = nums.length - 1;
		int result = 10000001;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (nums[mid] >= target) {
				result = nums[mid];
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}
		return result;
	}
}