import java.io.*;
import java.util.*;

public class Main {
	private static int[][] board;
	private static int[][] prefix;
	private static int[][] compPrefix;
	private static final int MAX = 101;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		board = new int[MAX][MAX];
		prefix = new int[MAX][MAX];
		compPrefix = new int[MAX][MAX];

		for (int i = 0; i < 2 * N; i += 2) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) + 1;
			int sc = Integer.parseInt(st.nextToken()) + 1;
			int er = sr + 10 - 1;
			int ec = sc + 10 - 1;

			for (int r = sr; r <= er; r++) {
				for (int c = sc; c <= ec; c++) {
					board[r][c] = 1;
				}
			}
		}

		for (int r = 1; r < MAX; r++) {
			int sum = 0;
			int tmp = 0;
			for (int c = 1; c < MAX; c++) {
				sum += board[r][c];
				tmp++;
				prefix[r][c] = prefix[r - 1][c] + sum;
				compPrefix[r][c] = compPrefix[r - 1][c] + tmp;
			}
		}

		int answer = 0;
		for (int sr = 1; sr < MAX; sr++) {
			for (int sc = 1; sc < MAX; sc++) {
				for (int er = sr; er < MAX; er++) {
					for (int ec = sc; ec < MAX; ec++) {
						if (sr == er && sc == ec)
							continue;

						int extent1 = calExtent(sr, sc, er, ec, prefix);
						int extent2 = calExtent(sr, sc, er, ec, compPrefix);

						if (extent1 < 0)
							break;

						if (extent1 == extent2) {
							answer = Math.max(answer, extent2);
						}
					}
				}
			}
		}
		System.out.println(answer);

	}

	private static int calExtent(int sr, int sc, int er, int ec, int[][] tmp) {
		return tmp[er][ec] - tmp[er][sc - 1] - tmp[sr - 1][ec] + tmp[sr - 1][sc - 1];
	}
}