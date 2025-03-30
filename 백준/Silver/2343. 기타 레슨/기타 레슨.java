import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, ans;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		ans = Integer.MAX_VALUE;

		int left = 0;
		int right = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			left = Math.max(left, arr[i]);
			right += arr[i];
		}

		System.out.println(binarySearch(left, right));

	}

	private static int binarySearch(int s, int e) {
		
		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (check(mid)) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}

		}
		return s;
	}

	private static boolean check(int mid) {
		int cnt = 0;
		int total = 0;
		for (int i = 0; i < N; i++) {
			if (total + arr[i] > mid) {
				total = 0;
				cnt++;
			}

			total += arr[i];
		}

		if (total > 0) {
			cnt++;
		}

		return cnt > M;
	}

}