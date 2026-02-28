import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, S, sr, sc;
	private static int[][] fishDelta = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
			{ 1, -1 } };
	private static int[][] sharkDelta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	private static List<Integer>[][] board;
	private static int[][] state;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = 4;
		answer = 0;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		state = new int[N][N];

		board = new ArrayList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				board[r][c] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;

			board[r][c].add(dir);
		}

		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken()) - 1;
		sc = Integer.parseInt(st.nextToken()) - 1;
		simulate();
		System.out.println(answer);
	}

	private static void simulate() {
		for (int i = 0; i < S; i++) {

			List<Integer>[][] copy = copyBoard();

			moveFish();
			moveShark();
			removeSmell();
			init(copy);
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				answer += board[r][c].size();
			}
		}
	}

	private static void init(List<Integer>[][] copy) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				board[r][c].addAll(copy[r][c]);
			}
		}
	}

	private static void removeSmell() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (state[r][c] > 0) {
					state[r][c]--;
				}
			}
		}
	}

	private static void moveShark() {
		int bestEat = -1;
		int bestPath = Integer.MAX_VALUE;

		for (int d1 = 0; d1 < 4; d1++) {
			for (int d2 = 0; d2 < 4; d2++) {
				for (int d3 = 0; d3 < 4; d3++) {
					int r = sr, c = sc;
					int eat = 0;

					boolean[][] visited = new boolean[N][N];

					// step 1
					r += sharkDelta[d1][0];
					c += sharkDelta[d1][1];
					if (!isRange(r, c))
						continue;
					if (!visited[r][c]) {
						eat += board[r][c].size();
						visited[r][c] = true;
					}

					r += sharkDelta[d2][0];
					c += sharkDelta[d2][1];
					if (!isRange(r, c))
						continue;
					if (!visited[r][c]) {
						eat += board[r][c].size();
						visited[r][c] = true;
					}

					r += sharkDelta[d3][0];
					c += sharkDelta[d3][1];
					if (!isRange(r, c))
						continue;
					if (!visited[r][c]) {
						eat += board[r][c].size();
						visited[r][c] = true;
					}

					int pathNum = (d1 + 1) * 100 + (d2 + 1) * 10 + (d3 + 1);

					if (eat > bestEat || (eat == bestEat && pathNum < bestPath)) {
						bestEat = eat;
						bestPath = pathNum;
					}
				}
			}
		}

		int[] dirs = { bestPath / 100, (bestPath / 10) % 10, bestPath % 10 };
		int r = sr, c = sc;

		for (int k = 0; k < 3; k++) {
			int dir = dirs[k] - 1;
			r += sharkDelta[dir][0];
			c += sharkDelta[dir][1];

			if (!board[r][c].isEmpty()) {
				board[r][c].clear();
				state[r][c] = 3;
			}
		}

		sr = r;
		sc = c;
	}

	private static void moveFish() {
		List<Integer>[][] newBoard = new ArrayList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				newBoard[r][c] = new ArrayList<>();
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int size = board[r][c].size() - 1;

				for (int fish = size; fish >= 0; fish--) {
					movingFish(r, c, board[r][c].get(fish), newBoard);
					board[r][c].remove(fish);
				}
			}
		}
		board = newBoard;
	}

	private static void movingFish(int r, int c, int dir, List<Integer>[][] newBoard) {
		for (int i = 0; i < 8; i++) {
			int nd = (dir - i + 8) % 8;

			int nr = r + fishDelta[nd][0];
			int nc = c + fishDelta[nd][1];

			if (isRange(nr, nc) && state[nr][nc] == 0 && !(nr == sr && nc == sc)) {
				newBoard[nr][nc].add(nd);
				return;
			}
		}

		newBoard[r][c].add(dir);
	}

	private static List<Integer>[][] copyBoard() {
		List<Integer>[][] copy = new ArrayList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copy[r][c] = new ArrayList<>();
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copy[r][c].addAll(board[r][c]);
			}
		}

		return copy;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}