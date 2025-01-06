import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static boolean[][] visited;
	private static int[][] map;
	private static Queue<int[]> q = new ArrayDeque<>();
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 2) {
					q.offer(new int[] { r, c, 0 });
				}

			}

		}

		visited = new boolean[N][M];
		
		bfs();
		
		
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<M; c++) {
				
				if(!visited[r][c] && map[r][c] == 1) {
					map[r][c] = -1;
				}
				
				System.out.print(map[r][c]+" ");
			}
			System.out.println();
		}

	}

	private static void bfs() {

		int[] start = q.peek();
		visited[start[0]][start[1]] = true;
		map[start[0]][start[1]] = start[2];

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];
				
				if(nr>=0 && nr<N && nc>= 0 && nc<M && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					map[nr][nc] = curr[2]+1;
					q.add(new int[] {nr, nc, map[nr][nc]});
				}
			}

		}

	}

}