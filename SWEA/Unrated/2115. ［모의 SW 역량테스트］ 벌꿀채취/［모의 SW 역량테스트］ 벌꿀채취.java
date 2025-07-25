import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, C;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			board = new int[N][N];
			int[][] dist = new int[N][N - M + 1];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[] info = new int[3];
			for (int r = 0; r < dist.length; r++) {
				for (int c = 0; c < dist[r].length; c++) {
					dist[r][c] = selectHoneyCal(r, c);
					if (info[2] < dist[r][c]) {
						info[2] = dist[r][c];
						info[0] = r;
						info[1] = c;
					}
				}
			}

			sb.append("#").append(t).append(" ").append(cal(info, dist)).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int cal(int[] info, int[][] dist) {
		
		for(int c = Math.max(0, info[1] - M + 1);
				c <= Math.min(dist[info[0]].length - 1, info[1] + M -1); c++) {
			dist[info[0]][c] = 0;
		}
		
		int max = 0;
		for (int r = 0; r < dist.length; r++) {
			for (int c = 0; c < dist[r].length; c++) {
				max = Math.max(max, dist[r][c]);
			}
		}

		return max + info[2];
	}

	/**
	 * 선택한 벌통 중에서 가능한 조합 중 가장 큰 값을 리턴
	 */

	private static int selectHoneyCal(int r, int c) {
		int max = 0;
		int[] arr = new int[M];

		for (int i = 0; i < M; i++) {
			arr[i] = board[r][c + i];
		}
		int limit = 1 << M;

		for (int mask = 0; mask < limit; mask++) {
			int sum = 0;
			int squreSum = 0;
			for (int i = 0; i < M; i++) {
				if ((mask & (1 << i)) != 0) {
					sum += arr[i];
					squreSum += (arr[i] * arr[i]);
				}
			}

			if (sum <= C) {
				max = Math.max(max, squreSum);
			}
		}

		return max;
	}

}