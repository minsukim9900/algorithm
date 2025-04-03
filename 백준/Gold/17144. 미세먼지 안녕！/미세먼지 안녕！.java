import java.io.*;
import java.util.*;

public class Main {
	private static int R, C, T, ans;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		ArrayList<int[]> dust = new ArrayList<>();
		ArrayList<int[]> machine = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] > 0) {
					dust.add(new int[] { r, c, board[r][c] });
					continue;
				}
				if (board[r][c] == -1) {
					machine.add(new int[] { r, c });
				}
			}
		}

		simulate(dust, machine);
		System.out.println(ans);
		
	}

	private static void simulate(ArrayList<int[]> dust, ArrayList<int[]> machine) {

		Queue<int[]> q = new ArrayDeque<>();
		q.addAll(dust);
		while (T-- > 0) {
			
			int size = q.size();
			ArrayList<int[]> diff = new ArrayList<>();
			// 1번 먼지 확산
			for (int i = 0; i < size; i++) {
				int[] curr = q.poll();
				board[curr[0]][curr[1]] = 0;
				int cnt = 0;

				for (int j = 0; j < 4; j++) {
					int nr = curr[0] + delta[j][0];
					int nc = curr[1] + delta[j][1];

					if (nr >= 0 && nr < R && nc >= 0 && nc < C && board[nr][nc] != -1) {
						diff.add(new int[] { nr, nc, curr[2] / 5 });
						cnt++;
					}
				}
				diff.add(new int[] { curr[0], curr[1], curr[2] - ((curr[2] / 5) * cnt) });
			}

			for (int[] w : diff) {
				board[w[0]][w[1]] += w[2];
			}
			// 2번 공기 청정기 가동
			for (int i = 0; i < machine.size(); i++) {
				int[] curr = machine.get(i);
				int r = curr[0];
				int c = curr[1];

				// 반시계 방향
				if (i == 0) {
					for (int j = c - 1; j >= 0; j--) {
						swap(r, j, r, j + 1);
					}

					for (int j = r - 1; j >= 0; j--) {
						swap(j, 0, j + 1, 0);
					}

					for (int j = 1; j <= C - 1; j++) {
						swap(0, j, 0, j - 1);

					}

					for (int j = 1; j <= r; j++) {
						swap(j, C - 1, j - 1, C - 1);
					}

					for (int j = C - 2; j > c; j--) {
						swap(r, j, r, j + 1);
					}

				} else {
					// 시계 방향
					for (int j = c - 1; j >= 0; j--) {
						swap(r, j, r, j + 1);
					}

					for (int j = r + 1; j < R; j++) {
						swap(j, 0, j - 1, 0);
					}

					for (int j = 1; j < C; j++) {
						swap(R - 1, j, R - 1, j - 1);

					}

					for (int j = R -2; j >= r; j--) {
						swap(j, C - 1, j + 1, C - 1);
					}

					for (int j = C - 2; j > c; j--) {
						swap(r, j, r, j + 1);
					}

				}
				
			}
			
			q = dustInput();
			

		}

	}
	
	private static Queue<int[]> dustInput() {
		Queue<int[]> q = new ArrayDeque<>();
		
		ans = 0;
		for(int r= 0; r<R; r++) {
			for(int c= 0; c<C; c++) {
				if(board[r][c] > 0) {
					ans += board[r][c];
					q.add(new int[] {r, c, board[r][c]});
				}
			}
		}
		return q;
	}
	
	private static void swap(int sr, int sc, int er, int ec) {
		if (board[er][ec] == -1) {
			board[sr][sc] = 0;
			return;
		}

		int tmp = board[sr][sc];
		board[sr][sc] = board[er][ec];
		board[er][ec] = tmp;
	}

}