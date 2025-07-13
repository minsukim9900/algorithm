import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static long M;
	private static long[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Long.parseLong(st.nextToken());

			nums = new long[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Long.parseLong(st.nextToken());
			}
			
			sb.append("#" + t + " ").append(binarySearch()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static long binarySearch() {
		long answer = 0;
		long s = 1;
		long e = 1L << 60;

		while (s <= e) {
			long mid = (s + e) / 2;

			if (isPoss(mid)) {
				answer = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return answer;
	}

	private static boolean isPoss(long v) {
		long cnt = 0;

		for (long w : nums) {
			cnt += (w / v);

			if (cnt >= M)
				return true;
		}

		return false;
	}
}