import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[] shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());

				if (board[r][c] == 9) {
					board[r][c] = 0;
					shark = new int[] { r, c, 2, 0, };
				}
			}
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int time = 0;

		while (true) {
			int[] fish = select(shark);
			
			if (fish == null) {
				break;
			}
			
			board[fish[0]][fish[1]] = 0;
			shark[0] = fish[0];
			shark[1] = fish[1];
			shark[3]++;

			if (shark[2] == shark[3]) {
				shark[2]++;
				shark[3] = 0;
			}
			time += fish[2];
		}

		return time;
	}

	private static int[] select(int[] s) {

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {

				if (o1[2] == o2[2]) {
					if (o1[0] == o2[0]) {
						return o1[1] - o2[1];
					}
					return o1[0] - o2[0];
				}

				return o1[2] - o2[2];
			}

		});

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {s[0], s[1], 0});
		boolean[][] visited = new boolean[N][N];
		visited[s[0]][s[1]] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && board[nr][nc] <= s[2]) {
					visited[nr][nc] = true;
					if (board[nr][nc] == 0 || board[nr][nc] == s[2]) {
						q.add(new int[] { nr, nc, curr[2] + 1});
					} else {
						q.add(new int[] { nr, nc, curr[2] + 1});
						pq.add(new int[] { nr, nc, curr[2] + 1});
					}

				}
			}
		}
		if (pq.isEmpty()) {
			return null;
		}

		return pq.poll();
	}
}