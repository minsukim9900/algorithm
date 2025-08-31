import java.io.*;
import java.util.*;

public class Main {
	private static int N, white, blue;
	private static int[][] board;
	private static int[][] prefix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		prefix = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			int sum = 0;
			for (int c = 1; c <= N; c++) {
				sum += board[r - 1][c - 1];
				prefix[r][c] = prefix[r - 1][c] + sum;
			}
		}
		search(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void search(int sr, int sc, int length) {
		if (length == 0) {
			return;
		}

		int er = sr + length - 1;
		int ec = sc + length - 1;
		int extent = (er - sr + 1) * (ec - sc + 1);
		int count = cal(sr, sc, er, ec);

		if (count == 0) {
			white++;
		} else if (count == extent) {
			blue++;
		} else {
			int nr = sr + length / 2;
			int nc = sc + length / 2;
			search(sr, sc, length / 2);
			search(sr, nc, length / 2);
			search(nr, sc, length / 2);
			search(nr, nc, length / 2);
		}
	}

	private static int cal(int sr, int sc, int er, int ec) {
		return prefix[er + 1][ec + 1] - (prefix[er + 1][sc] + prefix[sr][ec + 1]) + prefix[sr][sc];
	}
}