import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	private static int R, C, cheese;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];

		cheese = 0;

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					cheese++;
				}
				board[r][c] = tmp;
			}
		}
		
		result = cheese;
		int time = 0;
		
		if(cheese == 0) {
			System.out.println(time);
			System.out.println(result);
			return;
		}
		
		while(true) {
			
			visited = new boolean[R][C];
			
			bfs();
			
			time++;
			
			if(cheese == 0) {
				System.out.println(time);
				System.out.println(result);
				return;
			}
			
		}
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0 });
		visited[0][0] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
					if (!visited[nr][nc] && board[nr][nc] == 1) {
						board[nr][nc] = 0;
						visited[nr][nc] = true;
						
						cheese--;
						
					} else if (!visited[nr][nc] && board[nr][nc] == 0) {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
					}
				}
			}

		}
		
		if(cheese != 0) {
			result = cheese;
		}
	}

}
