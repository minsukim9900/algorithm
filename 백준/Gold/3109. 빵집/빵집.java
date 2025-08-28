import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {
	private static int R, C, answer;
	private static char[][] board;
	private static boolean[][] visited;
	private static boolean isEnd;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		visited = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			board[r] = br.readLine().toCharArray();
		}
		for (int r = 0; r < R; r++) {
			isEnd = false;
			dfs(r, 0);
		}
		System.out.println(answer);
	}

	private static void dfs(int r, int c) {
		if (c == C - 1) {
			isEnd = true;
			answer++;
			return;
		}
		visited[r][c] = true;

		if (board[r][c] == 'x') {
			return;
		}

		if (!isEnd && isRange(r - 1, c + 1) && !visited[r - 1][c + 1]) {
			dfs(r - 1, c + 1);
		}

		if (!isEnd && isRange(r, c + 1) && !visited[r][c + 1]) {
			dfs(r, c + 1);
		}

		if (!isEnd && isRange(r + 1, c + 1) && !visited[r + 1][c + 1]) {
			dfs(r + 1, c + 1);
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}