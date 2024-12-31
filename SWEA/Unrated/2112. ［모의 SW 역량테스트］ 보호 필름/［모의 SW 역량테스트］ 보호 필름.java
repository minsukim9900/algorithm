import java.io.*;
import java.util.*;

public class Solution {
	
	private static int D, W, K, h;
	private static int[][] film;
	private static int[] choice, select;
	private static int result;
	private static boolean min;

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
			min = false;
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					film[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i <= D; i++) {
				h = i;
				choice = new int[h];
				select = new int[h];
				dfs(0, 0);
				if (min) {
					break;
				}
			}

			System.out.println("#" + t + " " + result);
		}

	}


	private static void dfs(int num, int depth) {
		if (!min) {

			if (depth == h) {
				selectCell(0);
			} else {
				for (int i = num; i <= D-h+depth; i++) {
					choice[depth] = i;
					dfs(i + 1, depth + 1);
				}
			}

		}

	}

	private static void selectCell(int depth) {
		if (!min) {
			if (depth == h) {
				
				
				ArrayList<int[]> backup = new ArrayList<>();
				for(int i = 0; i<choice.length; i++) {
					backup.add(Arrays.copyOf(film[choice[i]], film[choice[i]].length));
				}
				inject();
				
				

				boolean[] test = new boolean[W];
				if (!min) {
					for (int r = 0; r < D - K + 1; r++) {
						for (int c = 0; c < W; c++) {

							if (!test[c]) {
								test[c] = verify(r, c);
							}

						}
					}
				}

				if (checking(test)) {
					min = true;
					result = h;
					return;
				}
				
				for(int i = 0; i<choice.length; i++) {
					int idx = choice[i];
					film[idx] = backup.get(i);
				}
				

			} else {

				for (int i = 0; i <= 1; i++) {
					select[depth] = i;
					selectCell(depth + 1);

				}

			}
		}

	}

	private static void inject() {
		
		
		for (int i = 0; i < choice.length; i++) {
			Arrays.fill(film[choice[i]], select[i]);
		}

	}

	private static boolean verify(int r, int c) {

		for (int i = r; i < r + K - 1; i++) {

			if (film[i][c] != film[i + 1][c]) {
				return false;
			}

		}

		return true;

	}

	private static boolean checking(boolean[] test) {

		for (int i = 0; i < test.length; i++) {
			if (!test[i]) {
				return false;
			}
		}

		return true;

	}
}