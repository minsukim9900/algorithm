import java.io.*;
import java.util.*;

public class Solution {
	private static int D, W, K;
	private static int[][] board;
	private static int answer;
	private static int[] A, B;

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
			A = new int[W];
			B = new int[W];
			Arrays.fill(A, 0);
			Arrays.fill(B, 1);

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

	/**
	 * @param cnt: 약품 투입 갯수
	 */
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
		board[depth] = A;
		simulate(depth + 1, cnt + 1);

		// 약품 B 주입
		board[depth] = B;
		simulate(depth + 1, cnt + 1);
		board[depth] = tmp;
	}

	private static boolean check() {
		for (int c = 0; c < W; c++) {
			int cnt = 1;
			int state = board[0][c];

			for (int r = 1; r < D; r++) {
				// 만약 이전 상태와 같다면 cnt 증가
				if (state == board[r][c]) {
					cnt++;
				} else { // 아니라면 cnt 1로 초기화, 상태 바꾸기
					state = board[r][c];
					cnt = 1;
				}

				// cnt 갯수가 K개 이상이라면 해당 열은 통과이기 때문에 break;
				if (cnt >= K) {
					break;
				}
			}

			// 해당 열이 통과하지 못한다면 기준 미만으로 false 반환
			if (cnt < K) {
				return false;
			}
		}
		// 모든 열이 통과되었기 때문에 true 반환
		return true;
	}
}