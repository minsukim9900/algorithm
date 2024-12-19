import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	private static class virus implements Comparable<virus> {
		int level;
		int r;
		int c;

		public virus(int level, int r, int c) {
			this.level = level;
			this.r = r;
			this.c = c;
		}

		public int compareTo(virus o) {
			return this.level - o.level;
		}

	}

	private static int S;
	private static int[][] board;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		board = new int[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			board[0][i] = 1;
			board[N + 1][i] = 1;
			board[i][0] = 1;
			board[i][N + 1] = 1;
		}

		ArrayList<virus> adj = new ArrayList<>();

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] != 0) {
					adj.add(new virus(board[r][c], r, c));
				}
			}
		}

		Collections.sort(adj);

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		Queue<int[]> Virus = new ArrayDeque<>();

		for (virus w : adj) {
			Virus.add(new int[] { w.level, w.r, w.c });
		}
		
		if(S != 0) {
			process(Virus);
		}
		System.out.println(board[X][Y]);

	}

	private static void process(Queue<int[]> Virus) {
		int time = 0;
		while (!Virus.isEmpty()) {
			
			
			int size = Virus.size();
			
			for(int t = 0; t<size; t++) {
				int[] curr = Virus.poll();
				
				for (int i = 0; i < 4; i++) {
					int level = curr[0];
					int nr = curr[1] + dr[i];
					int nc = curr[2] + dc[i];
					
					if(board[nr][nc] == 0) {
						board[nr][nc] = level;
						Virus.add(new int[] {level, nr, nc});
					}
					
				}
				
			}
			
			time++;
			
			if(time == S) {
				return;
			}
			
		}

	}

}
