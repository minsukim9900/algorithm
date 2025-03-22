import java.io.*;
import java.util.*;

public class Main {
	private static int[][] map;
	private static boolean[][] visited;
	private static int N, M, K;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			visited = new boolean[N][M];

			ArrayList<int[]> baechu = new ArrayList<>();
			map = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());

				map[r][c] = 1;
				baechu.add(new int[] { r, c });
			}
			int cnt = 0;
			for (int[] curr : baechu) {
				if(!visited[curr[0]][curr[1]]) {
					bfs(curr);
					cnt++;
				}
			}

			sb.append(cnt + "\n");
		}
		System.out.println(sb.toString());

	}

	private static void bfs(int[] curr) {
		visited[curr[0]][curr[1]] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(curr);

		while (!q.isEmpty()) {
			int[] c = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = c[0] + delta[i][0];
				int nc = c[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}

		}
	}
}