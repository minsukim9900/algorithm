import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N + 1][M + 1];

		int count = 0;

		int ar = 0;
		int ac = 0;
		boolean widthFlag = false;
		boolean heightFlag = false;

		for (int r = 1; r < N + 1; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < M + 1; c++) {
				int num = Integer.parseInt(st.nextToken());

				board[0][c] += num;
				board[r][0] += num;

				if (num == 1) {
					count++;
				}

				if (board[0][c] >= K) {
					heightFlag = true;
					ac = c;
				}

				board[r][c] = num;
			}

			if (board[r][0] >= K) {
				widthFlag = true;
				ar = r;
			}
		}

		int total = K << 1;

		int answer = total - count;
		sb.append(answer).append("\n");

		if (answer > 0) {
			PriorityQueue<int[]> pq = new PriorityQueue<>(
					(a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

			if (widthFlag && heightFlag) {
				sb.append(ar + " " + ac).append("\n");
			} else if (widthFlag) {
				int sc = 0;

				for (int c = 1; c < M + 1; c++) {

					if (board[ar][c] == 1) {
						sc = c;
						break;
					}
				}

				int ec = sc + K - 1;
				for (int i = 0; i < answer; i++) {
					pq.add(new int[] { ar, ec - i });
				}

			} else if (heightFlag) {
				int sr = 0;

				for (int r = 1; r < N + 1; r++) {

					if (board[r][ac] == 1) {
						sr = r;
						break;
					}
				}

				int er = sr + K - 1;
				for (int i = 0; i < answer; i++) {
					pq.add(new int[] { er - i, ac });
				}
			}

			while (!pq.isEmpty()) {
				int[] curr = pq.poll();
				sb.append(curr[0]).append(" ").append(curr[1]).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}