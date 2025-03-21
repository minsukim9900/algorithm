import java.io.*;
import java.util.*;

public class Main {
	private static int K, N;
	private static long[] arr;
	private static long max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new long[K];
		max = 0;
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			Math.max(arr[i], max);
		}

		System.out.println(binarySearch());

	}

	private static long binarySearch() {
		long s = 1;
		long e = Long.MAX_VALUE;
		long result = 0;

		while (s <= e) {
			long mid = (s >> 1) + (e >> 1);

			if (check(mid)) {
				result = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}

		}

		return result;

	}

	private static boolean check(long v) {
		long cnt = 0;
		for (int i = 0; i < K; i++) {
			cnt += (arr[i] / v);
			if (cnt >= N)
				return true;
		}
		return false;
	}
}