import java.io.*;
import java.util.*;

public class Solution {
	private static long N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Long.parseLong(br.readLine());
			sb.append("#" + t + " " + binarySearch() + "\n");
		}
		System.out.print(sb.toString());
		
		

	}

	private static long binarySearch() {
		long s = 1;
		long e = 10000000000L;
		long ans = 0;

		while (s <= e) {

			long mid = s + ((e - s) >> 1);
			long value = (mid * (mid + 1)) >> 1;

			if (N >= value) {
				ans = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		
		long v = (ans * (ans + 1)) >> 1;
		return N == v ? ans : -1;
	}
}