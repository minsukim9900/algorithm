import java.io.*;
import java.util.*;

public class Solution {

	private static int N;
	private static int[] items;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());

			items = new int[N];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				items[i] = Integer.parseInt(st.nextToken());
			}

			sb.append("#" + t + " ").append(cal() + "\n");

		}

		System.out.println(sb.toString());
	}

	private static long cal() {

		long sum = 0;
		int max = items[N - 1];
		for (int i = N - 2; i >= 0; i--) {

			if (max >= items[i]) {
				sum += (max - items[i]);
			} else {
				max = items[i];
			}

		}

		return sum;
	}
}
