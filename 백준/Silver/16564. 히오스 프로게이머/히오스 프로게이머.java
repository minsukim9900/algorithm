import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		int s = Integer.MAX_VALUE;
		int e = 0;

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			s = Math.min(s, nums[i]);
			e = Math.max(e, nums[i]);
		}

		e += K;
		Arrays.sort(nums);
		System.out.println(binarySearch(s, e, nums));
	}

	private static int binarySearch(int s, int e, int[] nums) {
		int answer = 0;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (check(mid, nums)) {
				s = mid + 1;
				answer = mid;
			} else {
				e = mid - 1;
			}

		}
		return answer;
	}

	private static boolean check(int value, int[] nums) {
		int min = Integer.MAX_VALUE;
		int k = K;

		for (int curr : nums) {
			int tmp = value - curr;

			if (k > 0 && tmp > 0) {
				if (k >= tmp) {
					curr += tmp;
					k -= tmp;
				} else {
					curr += k;
					k = 0;
				}
			}
			min = Math.min(min, curr);
		}
		return min >= value;
	}
}