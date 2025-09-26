import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int sc = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());

			for (int r = sr; r < er; r++) {
				for (int c = sc; c < ec; c++) {
					board[r][c] = 1;
				}
			}
		}
		List<Integer> answer = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 0) {
					answer.add(bfs(r, c));
				}
			}
		}

		Collections.sort(answer);
		sb.append(answer.size()).append("\n");
		for (Integer ans : answer) {
			sb.append(ans).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		int count = 0;
		board[r][c] = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cr = curr[0];
			int cc = curr[1];
			count++;

			for (int i = 0; i < 4; i++) {
				int nr = cr + delta[i][0];
				int nc = cc + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] == 0) {
					q.add(new int[] { nr, nc });
					board[nr][nc] = 1;
				}
			}
		}
		return count;
	}
}