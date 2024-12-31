import java.io.*;
import java.util.*;

public class Solution {

	private static int D, W, K, h;
	private static int[][] film;
	private static int[] choice, select;
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

			result = Integer.MAX_VALUE;

			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					film[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[] tmp = new int[W];
			dfs(0, tmp, 0);

			System.out.println("#" + t + " " + result);
		}

	}

	private static void dfs(int idx, int[] tmp, int cnt) {
		if (cnt > result) {
			return;
		}
		if (idx == D) {

			if (cnt > result) {
				return;
			}

			if (check()) {
				result = Math.min(result, cnt);
			}

		} else {

			dfs(idx + 1, tmp, cnt);

			tmp = film[idx].clone();
			Arrays.fill(film[idx], 0);
			dfs(idx + 1, tmp, cnt + 1);
			film[idx] = tmp;

			tmp = film[idx].clone();
			Arrays.fill(film[idx], 1);
			dfs(idx + 1, tmp, cnt + 1);
			film[idx] = tmp;

		}

	}

	private static boolean check() {

		for (int c = 0; c < W; c++) {
			boolean flag = false;
			int cnt = 1;
			for (int r = 1; r < D; r++) {
				
				if(film[r][c] == film[r-1][c]) {
					cnt++;
				}else {
					cnt = 1;
				}
				
				if(cnt >= K) {
					flag = true;
					break;
				}
			}
			
			if(!flag) return false;
			

		}
		
		return true;

	}

}
