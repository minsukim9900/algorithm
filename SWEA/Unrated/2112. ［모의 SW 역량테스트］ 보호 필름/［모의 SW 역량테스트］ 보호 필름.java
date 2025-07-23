import java.io.*;
import java.util.*;

public class Solution {
	private static int D, W, K;
	private static int[][] board;
	private static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = D;
			board = new int[D][W];
            
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			simulate(0, 0);
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void simulate(int depth, int cnt) {
		if (cnt >= answer) {
			return;
		}

		if (depth == D) {
			if (check()) {
				answer = Math.min(answer, cnt);
			}
			return;
		}

		// 약품 주입 X
		simulate(depth + 1, cnt);

		// 약품 A 주입
		int[] tmp = board[depth].clone();
		Arrays.fill(board[depth], 0);
		simulate(depth + 1, cnt + 1);

		// 약품 B 주입
		Arrays.fill(board[depth], 1);
		simulate(depth + 1, cnt + 1);
		board[depth] = tmp;
	}

	private static boolean check() {
		for (int c = 0; c < W; c++) {
			int cnt = 1;
			int state = board[0][c];

			for (int r = 1; r < D; r++) {
				if (state == board[r][c]) {
					cnt++;
				} else {
					state = board[r][c];
					cnt = 1;
				}
				
				if (cnt >= K) {
					break;
				}
			}
			
			if (cnt < K) {
				return false;
			}
		}
		return true;
	}
}