import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static long M;
	private static long[] candy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Long.parseLong(st.nextToken());
			candy = new long[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				candy[i] = Long.parseLong(st.nextToken());
			}
			
			sb.append("#" + t + " " + binarySearch() +"\n");
		}
		System.out.print(sb.toString());

	}

	private static long binarySearch() {
		long s = 1;
		long e = 1L << 60;
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
		long cnt = 0;
		
		for (int i = 0; i < N; i++) {
			cnt += candy[i] / mid;
			
			if(cnt >= M) return true;
		}
		return false;
	}
}