import java.io.*;
import java.util.*;

public class Main {
    
    private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int N;
	private static int[][] board;
	private static int[][] dist;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int time = 1;
		while(true) {
			
			N = read();
			if(N == 0) {
				break;
			}
			board = new int[N][N];
			dist = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					board[r][c] = read();
					dist[r][c] = Integer.MAX_VALUE;
				}
				
			}
			
			bfs();
			sb.append("Problem ").append(time).append(": ").append(dist[N-1][N-1]).append("\n");
			time++;
		}
		
		System.out.println(sb.toString());
	}

	private static void bfs() {

		dist[0][0] = board[0][0];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {

					int tmp = dist[curr[0]][curr[1]] + board[nr][nc];

					if (dist[nr][nc] > tmp) {
						dist[nr][nc] = tmp;
						q.offer(new int[] { nr, nc });
					}

				}
			}
			
		}

	}

}