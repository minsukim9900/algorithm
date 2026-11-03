import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M;
	private static int[] trees;

	private static boolean check(int height) {
		long sum = 0;

		for (int i = 0; i < N; i++) {
			sum += Math.max(0, trees[i] - height);

			if (sum >= M) {
				return true;
			}
		}

		return false;
	}

	private static int binarySearch(int s, int e) {
		int result = 0;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (check(mid)) {
				result = mid;

				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			trees = new int[N];

			st = new StringTokenizer(br.readLine());

			int max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				int height = Integer.parseInt(st.nextToken());

				trees[i] = height;

				max = Math.max(max, height);
			}

			sb.append("#").append(t).append(" ").append(binarySearch(0, max)).append("\n");

		}

		System.out.println(sb.toString());
	}
}
