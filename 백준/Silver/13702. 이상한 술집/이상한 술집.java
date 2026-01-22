import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long s = 1L;
		long e = (1L << 60);
		long answer = 0L;

		while (s <= e) {
			long mid = s + (e - s) / 2;

			if (check(mid)) {
				answer = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return answer;
	}

	private static boolean check(long mid) {
		int count = 0;

		for (int x : nums) {
			count += (x / mid);

			if (count >= K)
				return true;
		}
		return false;
	}
}