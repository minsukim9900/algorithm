import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, P;
	private static int[][] board;
	private static int[] S;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		S = new int[P + 1];
		count = new int[P + 1];
		Queue<int[]>[] q = new ArrayDeque[P + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= P; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			q[i] = new ArrayDeque<>();
		}

		board = new int[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				char tmp = str.charAt(c);
				board[r][c] = tmp == '.' ? 0 : tmp == '#' ? -1 : tmp - '0';

				if (0 < board[r][c] && board[r][c] < 10) {
					q[board[r][c]].add(new int[] { r, c });
				}
			}
		}
		bfs(q);
		for(int i = 1; i <= P; i++) {
			sb.append(count[i]).append(" " );
		}
		System.out.println(sb.toString());
	}

	private static void bfs(Queue<int[]>[] q) {
		while (true) {
			boolean expanded = false;

			for (int player = 1; player <= P; player++) {
				if (q[player].isEmpty())
					continue;

				for (int step = 0; step < S[player]; step++) {
					int size = q[player].size();

					if (size == 0)
						break;

					for (int i = 0; i < size; i++) {
						int[] curr = q[player].poll();
						count[player]++;
						int r = curr[0];
						int c = curr[1];

						for (int d = 0; d < 4; d++) {
							int nr = r + delta[d][0];
							int nc = c + delta[d][1];

							if (isRange(nr, nc) && board[nr][nc] == 0) {
								board[nr][nc] = player;
								q[player].add(new int[] { nr, nc });
								expanded = true;
							}
						}
					}
				}
			}

			if (!expanded)
				break;
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}