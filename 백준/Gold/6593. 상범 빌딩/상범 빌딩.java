import java.io.*;
import java.util.*;

public class Main {

	private static int L, N, M;
	private static int[][][] map;
	private static int[][][] dist;
	private static int[] dr = { -1, 1, 0, 0, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1, 0, 0 };
	private static int[] dl = { 0, 0, 0, 0, -1, 1 };
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {

			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (L == 0) {
				System.out.println(sb.toString());
				return;
			}

			map = new int[L][N][M];
			dist = new int[L][N][M];
			Queue<int[]> q = new ArrayDeque<>();
			int[] end = null;

			for (int l = 0; l < L; l++) {

				for (int r = 0; r < N; r++) {
					String state = br.readLine();

					for (int c = 0; c < M; c++) {
						char tmp = state.charAt(c);
						if (tmp == 'S') {
							q.add(new int[] { l, r, c });
						} else if (tmp == '.') {
							map[l][r][c] = 0;
						} else if (tmp == '#') {
							map[l][r][c] = 1;
						} else {
							end = new int[] { l, r, c };
						}

					}
				}
				String next = br.readLine();

			}

			bfs(q, end);

		}

	}

	private static void bfs(Queue<int[]> q, int[] end) {
		int[] curr = q.peek();
		dist[curr[0]][curr[1]][curr[2]] = 0;
		boolean[][][] visited = new boolean[L][N][M];
		visited[curr[0]][curr[1]][curr[2]] = true;

		while (!q.isEmpty()) {

			curr = q.poll();

			if (curr[0] == end[0] && curr[1] == end[1] && curr[2] == end[2]) {
				sb.append("Escaped in ").append(dist[end[0]][end[1]][end[2]]).append(" minute(s).").append("\n");
				return;
			}

			for (int i = 0; i < 6; i++) {
				int nl = curr[0] + dl[i];
				int nr = curr[1] + dr[i];
				int nc = curr[2] + dc[i];

				if (nl >= 0 && nl < L && nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nl][nr][nc]
						&& map[nl][nr][nc] == 0) {
					visited[nl][nr][nc] = true;
					dist[nl][nr][nc] = dist[curr[0]][curr[1]][curr[2]] + 1;
					q.offer(new int[] { nl, nr, nc });
				}

			}

		}

		sb.append("Trapped!").append("\n");
		return;

	}

}