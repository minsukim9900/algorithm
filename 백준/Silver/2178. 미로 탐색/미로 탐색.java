import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] maze;
	private static int[][] check;
	private static int cnt = 0;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		check = new int[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				maze[r][c] = str.charAt(c) - '0';
			}
		}

		bfs(0, 0, 1);
		System.out.println(check[N - 1][M - 1]);

	}

	public static void bfs(int r, int c, int depth) {
		Queue<int []> q = new ArrayDeque<>();
		q.add(new int[] {r, c, depth});
		check[r][c] = 1;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			for(int i = 0; i<4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				if(nr >= 0 && nr <N && nc >= 0 && nc<M) {
					int num = maze[nr][nc];
					if(num == 1 && check[nr][nc] == 0) {
						check[nr][nc] = curr[2]+1;
						q.add(new int[] {nr, nc, curr[2]+1});
					}
				}
			}
		}
	}

}
