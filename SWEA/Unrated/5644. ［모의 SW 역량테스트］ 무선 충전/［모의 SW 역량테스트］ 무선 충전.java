import java.io.*;
import java.util.*;

public class Solution {
	private static int M, A;
	private static int[][] delta = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static int[][][] board;
	private static int[][][] map;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			board = new int[10][10][2];
			visited = new boolean[A];
			map = new int[10][10][2];

			List<Integer> aOrder = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				aOrder.add(Integer.parseInt(st.nextToken()));
			}

			List<Integer> bOrder = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				bOrder.add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bfs(y, x, c, p, i + 1);
			}
			sb.append("#").append(t).append(" ").append(simulate(aOrder, bOrder)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(List<Integer> aOrder, List<Integer> bOrder) {
		List<int[]> arr = new ArrayList<>();
		arr.add(new int[] { 0, 0 });
		arr.add(new int[] { 9, 9 });
		int result = cal(arr);

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0 });
		q.add(new int[] { 9, 9 });

		for (int i = 0; i < M; i++) {
			int size = q.size();
			arr = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				int[] curr = q.poll();
				int nr = 0;
				int nc = 0;

				if (j == 0) {
					nr = curr[0] + delta[aOrder.get(i)][0];
					nc = curr[1] + delta[aOrder.get(i)][1];
				} else {
					nr = curr[0] + delta[bOrder.get(i)][0];
					nc = curr[1] + delta[bOrder.get(i)][1];
				}
				q.add(new int[] { nr, nc });
				arr.add(new int[] { nr, nc });
			}
			result += cal(arr);
		}
		return result;
	}

	private static int cal(List<int[]> arr) {
		int sum = 0;
		int[] a = arr.get(0);
		int[] b = arr.get(1);
		int aValue = map[a[0]][a[1]][0];
		int bValue = map[b[0]][b[1]][0];

		if (aValue > 0 && aValue == bValue) {
			int tmpA = 0;
			int tmpB = 0;

			if (map[a[0]][a[1]][1] > 0) {
				tmpA = board[a[0]][a[1]][1] + board[b[0]][b[1]][0];
			}

			if (map[b[0]][b[1]][1] > 0) {
				tmpB = board[b[0]][b[1]][1] + board[a[0]][a[1]][0];
			}
			return Math.max(tmpA, Math.max(tmpB, board[a[0]][a[1]][0]));
		}
		return board[a[0]][a[1]][0] + board[b[0]][b[1]][0];
	}

	private static void output() {
		for (int i = 0; i < 2; i++) {
			for (int r = 0; r < 10; r++) {
				for (int c = 0; c < 10; c++) {
					System.out.print(board[r][c][i] + "\t");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	private static void bfs(int y, int x, int c, int p, int idx) {
		boolean[][] visited = new boolean[10][10];
		Queue<int[]> q = new ArrayDeque<>();

		if (board[y][x][0] > p) {
			if (board[y][x][1] < p) {
				board[y][x][1] = p;
				map[y][x][1] = idx;
			}
		} else {
			board[y][x][0] = p;
			map[y][x][0] = idx;
		}

		visited[y][x] = true;
		q.add(new int[] { y, x, c });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 1; i <= 4; i++) {

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc] && curr[2] > 0) {

					if (board[nr][nc][0] > 0) {
						int tmp = board[nr][nc][0];

						if (tmp > p) {
							if (board[nr][nc][1] < p) {
								board[nr][nc][1] = p;
								map[nr][nc][1] = idx;
							}
						} else {
							board[nr][nc][0] = p;
							board[nr][nc][1] = tmp;
							map[nr][nc][1] = map[nr][nc][0];
							map[nr][nc][0] = idx;
						}

					} else {
						board[nr][nc][0] = p;
						map[nr][nc][0] = idx;
					}
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, curr[2] - 1 });
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < 10 && c >= 0 && c < 10;
	}
}