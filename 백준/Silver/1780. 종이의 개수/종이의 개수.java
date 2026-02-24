import java.io.*;
import java.util.*;

public class Main {
	private static int[][] board;
	private static int[] answer = new int[3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		search(0, 0, N);
		for(int ans : answer) {
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void search(int sr, int sc, int N) {
		int state = check(sr, sc, N);

		if (state >= -1 && state <= 1) {
			answer[state + 1]++;
			return;
		}

		int divideNum = N / 3;

		search(sr, sc, divideNum);
		search(sr, sc + divideNum, divideNum);
		search(sr, sc + (divideNum * 2), divideNum);

		search(sr + divideNum, sc, divideNum);
		search(sr + divideNum, sc + divideNum, divideNum);
		search(sr + divideNum, sc + (divideNum * 2), divideNum);

		search(sr + (divideNum * 2), sc, divideNum);
		search(sr + (divideNum * 2), sc + divideNum, divideNum);
		search(sr + (divideNum * 2), sc + (divideNum * 2), divideNum);
	}

	private static int check(int sr, int sc, int N) {
		int num = board[sr][sc];

		for (int r = sr; r < sr + N; r++) {
			for (int c = sc; c < sc + N; c++) {
				if (board[r][c] != num) {
					return 2;
				}
			}
		}

		return num;
	}
}