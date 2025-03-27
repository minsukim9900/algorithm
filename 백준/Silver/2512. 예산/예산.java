import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long M, total;
	private static long[] costs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		costs = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			costs[i] = Long.parseLong(st.nextToken());
			total = Math.max(total, costs[i]);
		}

		M = Long.parseLong(br.readLine());
		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long s = 0L;
		long e = total;
		long result = 0;

		while (s <= e) {
			long mid = s + (e - s) / 2;

			if (check(mid)) {
				result = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return result;
	}

	private static boolean check(long mid) {
		long sum = 0;

		for (int i = 0; i < N; i++) {
			if (costs[i] <= mid) {
				sum += costs[i];
			} else {
				sum += mid;
			}
		}
		return sum <= M;
	}
}