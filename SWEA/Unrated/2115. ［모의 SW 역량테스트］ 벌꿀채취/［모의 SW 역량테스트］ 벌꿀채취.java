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

			for (int r = 0; r < dist.length; r++) {
				for (int c = 0; c < dist[r].length; c++) {
					dist[r][c] = selectHoneyCal(r, c);
				}
			}
			sb.append("#").append(t).append(" ").append(cal(dist)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int cal(int[][] dist) {
		int answer = 0;

		for (int r = 0; r < dist.length; r++) {
			for (int c = 0; c < dist[r].length; c++) {
				int sum = dist[r][c];
				for (int nr = r; nr < dist.length; nr++) {
					for (int nc = 0; nc < dist[r].length; nc++) {
						if (r == nr && (nc <= c + M)) {
							continue;
						}
						answer = Math.max(answer,  sum + dist[nr][nc]);
					}
				}
			}
		}
		return answer;
	}

	/**
	 * 선택한 벌통 중에서 가능한 조합 중 가장 큰 값을 리턴
	 */
	private static int max;

	private static int selectHoneyCal(int r, int c) {
		max = 0;
		int[] arr = new int[M];

		for (int i = 0; i < M; i++) {
			arr[i] = board[r][c + i];
		}
		perm(0, arr, new boolean[arr.length], 0, 0);
		return max;
	}

	private static void perm(int depth, int[] arr, boolean[] visited, int sum, int total) {
		if (depth == arr.length) {
			max = Math.max(max, total);
		} else {
			for (int i = 0; i < arr.length; i++) {
				if (visited[i])
					continue;
				int num = 0;
				
				if (sum + arr[i] <= C) {
					num = arr[i];
				}
				visited[i] = true;
				perm(depth + 1, arr, visited, sum + num, total + (num * num));
				visited[i] = false;
			}
		}
	}
}