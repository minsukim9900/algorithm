import java.io.*;
import java.util.*;

public class Main {
	private static int I;
	private static int[][] delta = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 },
			{ 2, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			I = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());

			sb.append(bfs(sr, sc, er, ec)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		boolean[][] visited = new boolean[I][I];
		visited[sr][sc] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc, 0 });

		int answer = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int d = curr[2];

			if (r == er && c == ec) {
				answer = d;
				break;
			}
			for (int i = 0; i < delta.length; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, d + 1 });
				}
			}
		}
		return answer;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < I && c >= 0 && c < I;
	}
}