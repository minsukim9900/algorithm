import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(br.readLine());

			board = new int[N][N];

			List<int[]> arr = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());

					board[r][c] = num;
					arr.add(new int[] { r, c, num });
				}
			}

			arr.sort((a, b) -> Integer.compare(a[2], b[2]));

			boolean[][] visited = new boolean[N][N];
			int answerNum = 0;
			int answerCount = 0;
			for (int i = 0; i < arr.size(); i++) {
				int[] curr = arr.get(i);

				int sr = curr[0];
				int sc = curr[1];
				int num = curr[2];

				if (visited[sr][sc]) {
					continue;
				}

				int count = bfs(sr, sc, visited);

				if (answerCount < count) {
					answerNum = num;
					answerCount = count;
				}
			}

			sb.append(answerNum).append(" ").append(answerCount).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static int bfs(int sr, int sc, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();

		q.add(new int[] { sr, sc, board[sr][sc] });

		visited[sr][sc] = true;
		int result = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			result++;

			int r = curr[0];
			int c = curr[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] - board[r][c] == 1) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}

		return result;
	}
}
