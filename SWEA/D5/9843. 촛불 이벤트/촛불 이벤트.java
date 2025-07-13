import java.io.*;
import java.util.*;

public class Solution {
	private static long N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Long.parseLong(br.readLine());
			sb.append("#" + t + " ").append(binarySearch()).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static long binarySearch() {
		long answer = -1;
		long s = 1;
		long e = Integer.MAX_VALUE;

		while (s <= e) {
			long mid = (s + e) / 2;
			long num = cal(mid);
			
			
			if (num == N) {
				answer = mid;
				break;
			} else if (num > N) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return answer;
	}

	private static long cal(long v) {
		return (v * (v + 1)) / 2L;
	}
}