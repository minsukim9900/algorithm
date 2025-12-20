import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		char[][] board = new char[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j <= i; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		boolean isPoss = true;

		out: for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				if(!visited[i][j]) {
					
					if (board[i][j] == 'R') {
						if (!isRange(i + 1, j) || !isRange(i + 1, j + 1)) {
							isPoss = false;
							break out;
						}

						if (!(board[i + 1][j] == 'R' && board[i + 1][j + 1] == 'R')) {
							isPoss = false;
							break out;
						}

						if (visited[i][j] || visited[i + 1][j] || visited[i + 1][j + 1]) {
							isPoss = false;
							break out;
						}

						visited[i][j] = true;
						visited[i + 1][j] = true;
						visited[i + 1][j + 1] = true;
					} else if (board[i][j] == 'B') {
						if (!isRange(i, j + 1) || !isRange(i + 1, j + 1)) {
							isPoss = false;
							break out;
						}

						if (!(board[i][j + 1] == 'B' && board[i + 1][j + 1] == 'B')) {
							isPoss = false;
							break out;
						}

						if (visited[i][j] || visited[i][j + 1] || visited[i + 1][j + 1]) {
							isPoss = false;
							break out;
						}

						visited[i][j] = true;
						visited[i][j + 1] = true;
						visited[i + 1][j + 1] = true;
					}
				}
			}
		}

		if (isPoss) {
			out: for (int r = 0; r < N; r++) {
				for (int c = 0; c <= r; c++) {
					if (!visited[r][c]) {
						isPoss = false;
						break out;
					}
				}
			}
		}

		System.out.println(!isPoss ? 0 : 1);

	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < r + 1;
	}
}