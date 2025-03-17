import java.io.*;
import java.util.*;

public class Main {

	private static int result = 987654321;
	private static int[][] map = new int[21][21];
	private static int[][] board = new int[21][21];
	private static int[][] delta = { { 1, 0 }, { 1, 1 }, { 1, -1 }, { 0, 1 } };

	private static ArrayList<int[]> dolls = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int cnt = 1;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = cnt++;
			map[r][c] = ((i & 1) == 1) ? 1 : 2;
			dolls.add(new int[] { r, c });
		}

		int[] info = null;

		for (int[] curr : dolls) {

			info = dfs(curr[0], curr[1], 0, map[curr[0]][curr[1]]);
			if (info[2] == 5 && map[curr[0] - 1][curr[1]] != map[curr[0]][curr[1]]) {
				search(info, 0);
			}

			info = dfs(curr[0], curr[1], 1, map[curr[0]][curr[1]]);
			if (info[2] == 5 && map[curr[0] - 1][curr[1] - 1] != map[curr[0]][curr[1]]) {
				search(info, 1);
			}

			info = dfs(curr[0], curr[1], 2, map[curr[0]][curr[1]]);
			if (info[2] == 5 && map[curr[0] - 1][curr[1] + 1] != map[curr[0]][curr[1]]) {
				search(info, 2);
			}

			info = dfs(curr[0], curr[1], 3, map[curr[0]][curr[1]]);
			if (info[2] == 5 && map[curr[0]][curr[1] - 1] != map[curr[0]][curr[1]]) {
				search(info, 3);
			}

		}

		if (result == 987654321)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	private static void search(int[] info, int state) {

		int max = 0;

		if (state == 0) {
			for (int i = 0; i < 5; i++) {
				max = Math.max(board[info[0] + i][info[1]], max);
			}
		} else if (state == 1) {
			for (int i = 0; i < 5; i++) {
				max = Math.max(board[info[0] + i][info[1] + i], max);
			}
		} else if (state == 2) {
			for (int i = 0; i < 5; i++) {
				max = Math.max(board[info[0] + i][info[1] - i], max);
			}
		} else {
			for (int i = 0; i < 5; i++) {
				max = Math.max(board[info[0]][info[1] + i], max);
			}
		}

		result = Math.min(max, result);

	}

	private static int[] dfs(int r, int c, int dir, int color) {

		int nr = r;
		int nc = c;
		int nd = dir;
		int cnt = 1;
		int[] info = null;

		while (true) {

			nr += delta[nd][0];
			nc += delta[nd][1];

			if (!(nr >= 1 && nr < 20 && nc >= 1 && nc < 20)) {
				break;
			}

			if (map[nr][nc] != color) {
				break;
			}

			cnt++;
		}

		info = new int[] { r, c, cnt };

		return info;

	}

}