import java.io.*;
import java.util.*;

public class Main {
	private static int R, C, answer;
	private static int[][] board, seen;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		for (int r = 0; r < R; r++) {
			char[] tmp = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				board[r][c] = tmp[c] - 'A';
			}
		}
		
		seen = new int[R][C];
		for(int[] r : seen) {
			Arrays.fill(r, -1);
		}
		int visited = (1 << board[0][0]);
		dfs(0, 0, 1, visited);
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int count, int visited) {
		if(seen[r][c] == visited) return;
		seen[r][c] = visited;
		
		answer = Math.max(answer, count);
		if (answer == 26)
			return;

		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];
			if (isRange(nr, nc) && (visited & (1 << board[nr][nc])) == 0) {
				dfs(nr, nc, count + 1, visited | (1 << board[nr][nc]));
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}