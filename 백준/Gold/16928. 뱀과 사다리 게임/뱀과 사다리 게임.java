import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] board = new int[10][10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int[] info = seperate(from);

			board[info[0]][info[1]] = to;
		}
		
		System.out.println(simulate());
	}

	private static int simulate() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 0 });
		boolean[][] visited = new boolean[10][10];
		int rs = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[0] == 9 && curr[1] == 9) {
				rs = curr[2];
				break;
			}

			for (int i = 1; i <= 6; i++) {
				int nr = curr[0];
				int nc = curr[1] + i;

				if (nc >= 10) {
					nr = curr[0] + 1;
					nc = nc - 10;
				}
				
				if (nr < 10 && !visited[nr][nc]) {
					if (board[nr][nc] != 0) {
						int[] info = seperate(board[nr][nc]);
						nr = info[0];
						nc = info[1];
					}
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, curr[2] + 1 });
				}

			}
		}

		return rs;
	}

	private static int[] seperate(int x) {

		int sr = x / 10;
		int sc = x % 10;

		return new int[] { sr, sc };
	}
}