import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, D, max;
	private static int[][] board;
	private static int[][] delta = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
	private static int[][] loc;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		board = new int[N + 1][M];
		loc = new int[3][2];
		loc[0][0] = N;
		loc[1][0] = N;
		loc[2][0] = N;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);
		System.out.println(max);
	}

	private static void comb(int depth, int idx) {
		if (depth == 3) {
			max = Math.max(max, gameSimulate());
			return;
		}

		for (int i = idx; i < M; i++) {
			loc[depth][1] = i;
			comb(depth + 1, i + 1);
		}
	}

	private static int gameSimulate() {
		int count = 0;

		int[][] newBoard = new int[N + 1][M];
		for (int i = 0; i < N + 1; i++) {
			newBoard[i] = board[i].clone();
		}

		for(int i = 0; i < N; i++) {
			count += fire(newBoard);
		}
		return count;
	}

	private static int fire(int[][] newBoard) {
		boolean[][][] visited = new boolean[3][N + 1][M];

		int[][] target = new int[3][3];
		boolean[] found = new boolean[3];

		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < 3; i++) {
			q.add(new int[] { loc[i][0], loc[i][1], i, 0, D });
			visited[i][loc[i][0]][loc[i][1]] = true;
			Arrays.fill(target[i], 30);
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int index = curr[2];
			int distance = curr[3];
			int range = curr[4];
			
			if(!found[index] && newBoard[r][c] == 1) {
				target[index][0] = r;
				target[index][1] = c;
				target[index][2] = distance;
				found[index] = true;
			}


			if (range - 1 >= 0) {
				for (int i = 0; i < 3; i++) {
					int nr = r + delta[i][0];
					int nc = c + delta[i][1];

					if (isRange(nr, nc) && !visited[index][nr][nc]) {
						visited[index][nr][nc] = true;
						q.add(new int[] { nr, nc, index, distance + 1, range - 1 });
					}
				}
			}
		}

		boolean[][] isTargetDown = new boolean[N][M];
		int result = 0;
		for (int i = 0; i < 3; i++) {
			if (target[i][0] == 30 || isTargetDown[target[i][0]][target[i][1]])
				continue;

			result++;
			isTargetDown[target[i][0]][target[i][1]] = true;
			newBoard[target[i][0]][target[i][1]] = 0;
		}
		
		
		for(int i = N - 1; i > 0; i--) {
			newBoard[i] = newBoard[i - 1].clone();
		}
		
		newBoard[0] = new int[M];
		
		return result;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N + 1 && c >= 0 && c < M;
	}
}
