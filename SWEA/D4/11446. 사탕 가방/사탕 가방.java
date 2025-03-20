import java.io.*;
import java.util.*;

import javax.lang.model.type.UnionType;

public class Solution {
	private static int N;
	private static long M;
	private static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Long.parseLong(st.nextToken());

			arr = new long[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}

			long answer = cal();
			sb.append("#" + t + " " + answer + "\n");
		}
		System.out.println(sb.toString());
	}

	private static long cal() {
		long answer = 0;
		long left = 1;
		long right = 1L << 60;

		while (left <= right) {
			long mid = (left + right) >> 1;
			if (check(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		return answer;
	}

	private static boolean check(long v) {
		// 몇 개의 사탕을 넣을 수 있는지
		long cnt = 0;

		for (int i = 0; i < N; i++) {
			cnt += arr[i] / v;
		}

		return cnt >= M;
	}

}