import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static Queue<int[]> ice = new ArrayDeque<>();
	private static int[][] ocean;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ocean = new int[N + 2][M + 2];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 1; c <= M; c++) {
				ocean[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int years = 0;
		
		out : while (true) {
			visited = new boolean[N + 2][M + 2];
			boolean check = false;
			int[] start = null;

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					if (!check && ocean[r][c] > 0) {
						start = new int[] { r, c };
					}

				}
			}
			dfs(start);
			
			for(int r = 1; r<=N; r++ ) {
				for(int c = 1; c<=M; c++) {
					if( !visited[r][c] && ocean[r][c] > 0) {
						break out;
					}
				}
			}
			bfs();
			int sum = 0;
			for(int r = 1; r<=N; r++ ) {
				for(int c = 1; c<=M; c++) {
					sum += ocean[r][c];
				}
			}
			if(sum == 0) {
				years = 0;
				break;
			}
			
			years++;

		}
		
		System.out.println(years);


	}

	private static void dfs(int[] start) {
		ice.add(start);
		visited[start[0]][start[1]] = true;
		for (int i = 0; i < 4; i++) {
			int nr = start[0] + dr[i];
			int nc = start[1] + dc[i];

			if (!visited[nr][nc] && ocean[nr][nc] > 0) {
				int[] iceMount = new int[] { nr, nc };
				dfs(iceMount);
			}
		}

	}

	private static void bfs() {

		Queue<int[]> years = new ArrayDeque<>();

		while (!ice.isEmpty()) {
			int cnt = 0;
			int[] curr = ice.poll();

			for (int i = 0; i < 4; i++) {

				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				if (ocean[nr][nc] == 0) {
					cnt++;
				}
			}

			years.add(new int[] { curr[0], curr[1], cnt });

		}

		process(years);

	}

	private static void process(Queue<int[]> years) {

		while (!years.isEmpty()) {
			int[] curr = years.poll();
			ocean[curr[0]][curr[1]] -= curr[2];
			if (ocean[curr[0]][curr[1]] < 0) {
				ocean[curr[0]][curr[1]] = 0;
			}
		}

	}

}
