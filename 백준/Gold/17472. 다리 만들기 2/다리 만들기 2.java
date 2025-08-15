import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static List<int[]> startPoint;
	private static List<int[]> edges;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		startPoint = new ArrayList<>();
		edges = new ArrayList<>();

		List<int[]> lands = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());

				if (board[r][c] == 1) {
					lands.add(new int[] { r, c });
				}
			}
		}

		boolean[][] visited = new boolean[N][M];
		int idx = 1;
		for (int[] land : lands) {
			if (visited[land[0]][land[1]])
				continue;
			devideLand(land[0], land[1], visited, idx++);
		}

		for (int[] start : startPoint) {
			int r = start[0];
			int c = start[1];
			for (int dir = 0; dir < 4; dir++) {
				findOthersIsland(r, c, dir, board[r][c], board[r][c], 0);
			}
		}
		
		p = new int[idx];
		for (int i = 1; i < idx; i++) {
			p[i] = i;
		}

		Collections.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

		int pick = 0;
		int answer = 0;

		for (int i = 0; i < edges.size(); i++) {
			int[] curr = edges.get(i);
			int px = findP(curr[0]);
			int py = findP(curr[1]);

			if (px != py) {
				answer += curr[2];
				union(px, py);
				pick++;
			}
		}
		System.out.println(pick == idx - 2 ? answer : -1);
	}

	private static void union(int x, int y) {
		p[y] = x;
	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}
		return p[x];
	}

	private static void output() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static void findOthersIsland(int r, int c, int dir, int startAddress, int currAddress, int length) {
		if (currAddress > 0 && startAddress != currAddress) {
			if (length > 2) {
				edges.add(new int[] { startAddress, currAddress, length - 1 });
			}
			return;
		}

		r += delta[dir][0];
		c += delta[dir][1];
		if (isRange(r, c) && board[r][c] != startAddress) {
			findOthersIsland(r, c, dir, startAddress, board[r][c], length + 1);
		}
		return;
	}

	private static void devideLand(int r, int c, boolean[][] visited, int num) {
		board[r][c] = num;
		visited[r][c] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			boolean isEdge = false;
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc]) {
					if (board[nr][nc] == 1) {
						board[nr][nc] = num;
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc });
					} else {
						// 0을 만날 때
						isEdge = true;
					}
				}
			}
			if (isEdge) {
				startPoint.add(new int[] { curr[0], curr[1], num });
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}