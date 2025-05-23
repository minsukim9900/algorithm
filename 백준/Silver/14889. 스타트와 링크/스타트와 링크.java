import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		answer = Integer.MAX_VALUE;
		board = new int[N + 1][N + 1];
		
		int total = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				board[0][j] += board[i][j];
				board[i][0] += board[i][j];
				total += board[i][j];
			}
		}
		combi(1, 0, 0, total);
		System.out.println(answer);
	}

	private static void combi(int idx, int depth, int sum, int total) {
		if (depth == (N / 2)) {
			answer = Math.min(answer, Math.abs(total - sum));
			return;	
		}

		for (int i = idx; i <= N; i++) {
			sum += (board[0][i] + board[i][0]);
			combi(i + 1, depth +1, sum, total);
			sum -= (board[0][i] + board[i][0]);
		}
	}

}