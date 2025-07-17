import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] prices;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int max = 0;
		prices = new int[N];
		for (int i = 0; i < N; i++) {
			prices[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, prices[i]);
		}
		
		System.out.println(binarySearch(max));
	}

	private static int binarySearch(int start) {
		int result = 0;
		int s = start;
		int e = 1_000_000_000;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (check(mid)) {
				result = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return result;
	}

	private static boolean check(int v) {
		int cnt = 0;
		int money = 0;
		for (int i = 0; i < N; i++) {
			if (money < prices[i]) {
				cnt++;
				money = v;
			}
			money -= prices[i];
		}
		return cnt <= M;
	}
}
