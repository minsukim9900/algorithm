import java.io.*;
import java.util.*;

public class Main {
	private static int M, N;
	private static int[] nums;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		nums = new int[N];
        
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int answer = 0;
		int s = 1;
		int e = 1_000_000_000;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (check(mid)) {
				s = mid + 1;
				answer = mid;
			} else {
				e = mid - 1;
			}
		}
		return answer;
	}

	private static boolean check(int v) {
		int cnt = 0;

		for (int i : nums) {
			cnt += i / v;
		}
		return cnt >= M;
	}
}
