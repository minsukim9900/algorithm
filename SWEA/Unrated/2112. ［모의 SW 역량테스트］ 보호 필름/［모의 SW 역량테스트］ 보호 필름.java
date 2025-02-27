import java.io.*;
import java.util.*;

public class Solution {

	private static int D, W, K;
	private static int[][] pilm;
	private static int min;
	private static int[] A, B;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			min = 987654321;
			pilm = new int[D][W];

			A = new int[W];
			B = new int[W];
			Arrays.fill(B, 1);

			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					pilm[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 0);

			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb.toString());

	}

	private static void dfs(int r, int cnt) {

		if (isClear()) {
			min = Math.min(min, cnt);
		}

		if (cnt > min || r >= D)
			return;

		dfs(r + 1, cnt);

		int[] tmpA = pilm[r];
		pilm[r] = A;
		dfs(r + 1, cnt + 1);
		pilm[r] = tmpA;

		int[] tmpB = pilm[r];
		pilm[r] = B;
		dfs(r + 1, cnt + 1);
		pilm[r] = tmpB;

	}

	private static boolean isClear() {

		for (int c = 0; c < W; c++) {

			int cnt = 1;
			boolean flag = false;
			
			for (int r = 1; r < D; r++) {

				if (pilm[r][c] == pilm[r - 1][c]) {
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