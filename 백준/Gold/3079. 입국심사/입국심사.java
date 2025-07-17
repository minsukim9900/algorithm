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

		for (int i = 0; i < N; i++) {
			times[i] = Long.parseLong(br.readLine());
		}
		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long answer = 0L;
		long s = 0L;
		long e = Long.MAX_VALUE;

		while (s <= e) {
			long mid = (s >> 1) + (e >> 1);

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
			
			if(cnt >= M) {
				return true;
			}
		}
		return false;
	}
}
