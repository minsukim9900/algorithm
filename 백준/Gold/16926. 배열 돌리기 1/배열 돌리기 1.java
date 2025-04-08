import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, R;
	private static int[][] board;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		lotate();
		output();

	}

	private static void lotate() {

		int tmp = Math.min(N, M) >> 1;

		for (int i = 0; i < tmp; i++) {
			ArrayList<Integer> arr = new ArrayList<>();

			for (int c = i; c < M - i; c++) {
				arr.add(board[0 + i][c]);
			}
			for (int r = i + 1; r < N - i; r++) {
				arr.add(board[r][M - 1 - i]);
			}
			for (int c = M - 2 - i; c >= 0 + i; c--) {
				arr.add(board[N - 1 - i][c]);
			}
			for (int r = N - 2 - i; r > 0 + i; r--) {
				arr.add(board[r][0 + i]);
			}

			int currIdx = (0 + R) % arr.size();
			for (int c = i; c < M - i; c++) {
				board[0 + i][c] = arr.get(currIdx);
				currIdx = (currIdx + 1) % arr.size();
			}
			for (int r = i + 1; r < N - i; r++) {
				board[r][M - 1 - i] = arr.get(currIdx);
				currIdx = (currIdx + 1) % arr.size();
			}
			for (int c = M - 2 - i; c >= 0 + i; c--) {
				board[N - 1 - i][c] = arr.get(currIdx);
				currIdx = (currIdx + 1) % arr.size();
			}
			for (int r = N - 2 - i; r > 0 + i; r--) {
				board[r][0 + i] = arr.get(currIdx);
				currIdx = (currIdx + 1) % arr.size();
			}
		}

	}

	private static void output() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(board[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}