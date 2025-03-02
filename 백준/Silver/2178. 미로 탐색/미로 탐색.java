import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		
		System.out.println(bfs());

	}

	private static int bfs() {

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 1 });
		visited[0][0] = true;

		int cnt = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			
			if(curr[0] == N -1 && curr[1] == M -1) {
				cnt = curr[2];
				break;
			}

			for (int i = 0; i < 4; i++) {

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc, curr[2] + 1 });
				}

			}
		}
		
		return cnt;

	}
}
