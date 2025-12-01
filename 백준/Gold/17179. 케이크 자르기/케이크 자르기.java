import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, L;
	private static int target;
	private static int[] panel;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		panel = new int[M + 1];

		for (int i = 0; i < M; i++) {
			panel[i] = Integer.parseInt(br.readLine());
		}
		panel[M] = L;

		for (int i = 0; i < N; i++) {
			target = Integer.parseInt(br.readLine());
			sb.append(binarySearch()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int binarySearch() {
		int s = 0;
		int e = L;
		int answer = -1;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (check(mid)) {
				answer = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return answer;
	}

	private static boolean check(int len) {
		int count = 0;
		int pre = 0;

		for (int i = 0; i < M + 1; i++) {
			if (panel[i] - pre >= len) {
				pre = panel[i];
				count++;
			}

			if (count == target + 1)
				return true;
		}
		return false;
	}
}