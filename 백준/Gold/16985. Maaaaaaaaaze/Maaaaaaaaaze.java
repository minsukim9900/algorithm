import java.io.*;
import java.util.*;

public class Main {

	private static int[] dy = { 1, -1, 0, 0, 0, 0 };
	private static int[] dx = { 0, 0, 1, -1, 0, 0 };
	private static int[] dz = { 0, 0, 0, 0, 1, -1 };
	private static Map<Integer, int[][][]> map = new HashMap<>();
	private static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int i = 0; i < 5; i++) {
			int[][] board = new int[5][5];
			for (int r = 0; r < 5; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 5; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			map.put(i, rotation(board));
		}

		dfs(0, new boolean[5], new int[5][5][5]);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void dfs(int depth, boolean[] visited, int[][][] maze) {
		if(answer == 12) return;
		
		if (depth == 5) {
			if (maze[0][0][0] == 1 && maze[4][4][4] == 1) {
				simulate(maze);
			}
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			for (int j = 0; j < 4; j++) {
				maze[depth] = map.get(i)[j];
				dfs(depth + 1, visited, maze);
			}
			visited[i] = false;
		}
	}

	private static void simulate(int[][][] maze) {
		boolean[][][] visited = new boolean[5][5][5];
		visited[0][0][0] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 0, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (curr[3] > answer)
				break;

			if (isEnd(curr)) {
				answer = Math.min(answer, curr[3]);
				break;
			}

			for (int i = 0; i < 6; i++) {
				int ny = curr[0] + dy[i];
				int nx = curr[1] + dx[i];
				int nz = curr[2] + dz[i];

				if (isRange(ny, nx, nz) && maze[nz][ny][nx] == 1 && !visited[nz][ny][nx]) {
					visited[nz][ny][nx] = true;
					q.add(new int[] { ny, nx, nz, curr[3] + 1 });
				}
			}
		}
	}

	private static boolean isEnd(int[] curr) {
		for (int i = 0; i < 3; i++) {
			if (curr[i] != 4)
				return false;
		}
		return true;
	}

	private static boolean isRange(int y, int x, int z) {
		return y >= 0 && y < 5 && x >= 0 && x < 5 && z >= 0 && z < 5;
	}

	private static int[][][] rotation(int[][] board) {
		int[][][] result = new int[4][5][5];
		result[0] = board;

		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				result[1][4 - c][r] = board[r][c];
				result[2][4 - r][4 - c] = board[r][c];
				result[3][c][4 - r] = board[r][c];
			}
		}
		return result;
	}
}
