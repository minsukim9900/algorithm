import java.io.*;
import java.util.*;

public class Main {

	private static int[][] board;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	private static class Virus implements Comparable<Virus> {
		int level, r, c;

		public Virus(int level, int r, int c) {
			super();
			this.level = level;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Virus o) {
			// TODO Auto-generated method stub
			return this.level - o.level;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		ArrayList<Virus> virus = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int level = Integer.parseInt(st.nextToken());
				if (level != 0) {
					virus.add(new Virus(level, r, c));
				}
				board[r][c] = level;
			}
		}

		Collections.sort(virus);
		Queue<int[]> sortVirus = new ArrayDeque<>();

		for (Virus v : virus) {
			sortVirus.offer(new int[] { v.level, v.r, v.c });
		}

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		
		spread(N, S, sortVirus);
		System.out.println(board[x][y]);

	}

	private static void spread(int N, int S, Queue<int[]> virus) {

		int time = 0;
		while (time != S) {

			int size = virus.size();
			for (int i = 0; i < size; i++) {
				int[] curr = virus.poll();

				for (int j = 0; j < 4; j++) {
					int nr = curr[1] + dr[j];
					int nc = curr[2] + dc[j];

					if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) {
						board[nr][nc] = curr[0];
						virus.offer(new int[] {curr[0], nr, nc});
					}

				}

			}
			
			time++;
			if(time == S) {
				break;
			}

		}

	}

}
