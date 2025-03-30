import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long[] arr;
	private static long min, ans1, ans2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		min = Long.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			binarySearch(i + 1, N - 1, i);
		}
		
		System.out.println(ans1 + " " + ans2);
	}

	private static void binarySearch(int s, int e, int target) {

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (check(mid, target)) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

	}

	private static boolean check(int mid, int target) {
		long sum = arr[mid] + arr[target];

		if (min > Math.abs(sum)) {
			ans1 = arr[target];
			ans2 = arr[mid];
			min = Math.abs(sum);
		}

		if (sum >= 0)
			return true;
		return false;

	}
}