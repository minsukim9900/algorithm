import java.io.*;
import java.util.*;

public class Main {

	private static int w, h;
	private static int[][] map;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
			{ 1, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {

			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());

			if (w == 0)
				break;

			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			visited = new boolean[h][w];

			ArrayList<int[]> island = new ArrayList<>();

			for (int r = 0; r < h; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < w; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());

					if (map[r][c] == 1) {
						island.add(new int[] { r, c });
					}

				}
			}
			
			sb.append(search(island)).append("\n");
		}
		
		System.out.println(sb.toString());

	}

	private static int search(ArrayList<int[]> island) {
		int cnt = 0;

		for (int i = 0; i < island.size(); i++) {
			int[] curr = island.get(i);

			if (!visited[curr[0]][curr[1]]) {
				cnt++;
				bfs(curr[0], curr[1]);
			}

		}

		return cnt;
	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 8; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}

			}
		}

	}

}