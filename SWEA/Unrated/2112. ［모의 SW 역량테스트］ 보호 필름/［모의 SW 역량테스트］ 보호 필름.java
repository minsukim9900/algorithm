import java.io.*;
import java.util.*;

public class Solution {

	private static int D, W, K, h;
	private static int[][] film;
	private static int[] A, B;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];

			A = new int[W];
			B = new int[W];
			Arrays.fill(B, 1);

			result = Integer.MAX_VALUE;

			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					film[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 0);

			System.out.println("#" + t + " " + result);
		}

	}

	private static void dfs(int idx, int cnt) {
		if (check()) {
			result = Math.min(result, cnt);
			return;
		}
		if (cnt > result) {
			return;
		}
		if (idx == D) {
			return;
		} else {

			dfs(idx + 1, cnt);

			int[] tmp = film[idx];
			film[idx] =A;
			dfs(idx + 1, cnt + 1);
			film[idx] = tmp;
			
			film[idx] = B;
			dfs(idx + 1, cnt + 1);
			film[idx] = tmp;

		}

	}

	private static boolean check() {

		for (int c = 0; c < W; c++) {
			boolean flag = false;
			int cnt = 1;
			for (int r = 1; r < D; r++) {

				if (film[r][c] == film[r - 1][c]) {
					cnt++;
				} else {
					cnt = 1;
				}

				if (cnt >= K) {
					flag = true;
					break;
				}
			}

			if (!flag)
				return false;

		}

		return true;

	}

}
