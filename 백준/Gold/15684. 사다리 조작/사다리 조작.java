import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, H, answer;
	private static int[][] info;
	private static final int R = 31;
	private static final int C = 11;
	private static boolean isEnd;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		info = new int[R][C];
		isEnd = false;
		answer = -1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			info[x][y] = y + 1;
			info[x][y + 1] = y;
		}

		for (int i = 0; i <= 3 && !isEnd; i++) {
			addLine(0, i, 1, 1);
		}
		System.out.println(answer);
	}

	private static void addLine(int depth, int K, int ir, int ic) {
		if (isEnd)
			return;

		if (depth == K) {
			if (isPoss()) {
				answer = K;
				isEnd = true;
			}
			return;
		}

		for (int r = ir; r <= H; r++) {

			int sc = (r == ir) ? ic : 1;
			for (int c = sc; c < N; c++) {
				if (info[r][c] > 0 || info[r][c + 1] > 0)
					continue;

				info[r][c] = c + 1;
				info[r][c + 1] = c;

				addLine(depth + 1, K, ir, ic + 2);

				info[r][c] = 0;
				info[r][c + 1] = 0;
			}
		}
	}

	private static boolean isPoss() {
		for (int i = 1; i <= N; i++) {
			int c = i;
			for (int r = 1; r <= H; r++) {

				if (info[r][c] > 0) {
					c = info[r][c];
				}
			}

			if (c != i)
				return false;
		}

		return true;
	}
}
