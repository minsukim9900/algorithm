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
	    int N = dist.length;
	    int W = N - M + 1;
	    int answer = 0;

	    // 1) 같은 행 내에서 겹치지 않는 두 구간
	    for (int r = 0; r < N; r++) {
	        // suffixMax[c] = c열부터 끝까지의 최대 이익
	        int[] suffixMax = new int[W + 1];
	        suffixMax[W] = 0;
	        for (int c = W - 1; c >= 0; c--) {
	            suffixMax[c] = Math.max(suffixMax[c+1], dist[r][c]);
	        }
	        // c열 구간 + (c+M)열 이후 구간 조합
	        for (int c = 0; c + M < W; c++) {
	            answer = Math.max(answer, dist[r][c] + suffixMax[c + M]);
	        }
	    }

	    // 2) 서로 다른 행 조합
	    // bestBelow[r] = 행 r 이상에서의 최대 이익
	    int[] bestBelow = new int[N + 1];
	    bestBelow[N] = 0;
	    for (int r = N - 1; r >= 0; r--) {
	        int rowMax = 0;
	        for (int c = 0; c < W; c++) {
	            rowMax = Math.max(rowMax, dist[r][c]);
	        }
	        bestBelow[r] = Math.max(bestBelow[r+1], rowMax);
	    }
	    // (r,c) + bestBelow[r+1]
	    for (int r = 0; r < N; r++) {
	        for (int c = 0; c < W; c++) {
	            answer = Math.max(answer, dist[r][c] + bestBelow[r+1]);
	        }
	    }

	    return answer;
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