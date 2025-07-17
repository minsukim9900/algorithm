import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static long[] times;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		times = new long[N];
		
		long min = Long.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			times[i] = Long.parseLong(br.readLine());
			min = Math.min(min, times[i]);
		}
		System.out.println(binarySearch(min));
	}

	private static long binarySearch(long min) {
		long answer = 0L;
		long s = min;
		long e = min * M;

		while (s <= e) {
			long mid = (s + e) / 2;

			if (check(mid)) {
				e = mid - 1;
				answer = mid;
			} else {
				s = mid + 1;
			}
		}
		return answer;
	}

	private static boolean check(long v) {
		long cnt = 0;

		for (long w : times) {
			cnt += (v / w);
		}
		return cnt >= M;
	}
}
