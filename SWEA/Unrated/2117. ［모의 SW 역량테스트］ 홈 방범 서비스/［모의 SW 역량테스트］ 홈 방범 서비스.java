import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M;
	private static int[][] map;
	private static int[] price;
	private static int[][] delta = { { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		price = new int[30];

		for (int i = 1; i < 30; i++) {
			price[i] = (i * i) + ((i - 1) * (i - 1));
		}

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int r = 0; r < N; r++) {

				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {

					map[r][c] = Integer.parseInt(st.nextToken());
				}

			}

			int max = 0;
			// N의 범위가 21까지 주어짐
			for (int k = 1; k <= 21; k++) {

				for (int r = 0; r < N; r++) {

					for (int c = 0; c < N; c++) {

						// 각 좌표에 k를 증가시켜주면서 탐색
						int cnt = cntHouse(new int[] { r, c }, k);

						// 이윤 계산
						int profit = M * cnt - price[k];

						if (profit >= 0) {

							if (max < cnt) {
								max = cnt;
							}

						}

					}

				}

			}

			sb.append("#" + t + " " + max + "\n");

		}
		System.out.println(sb.toString());

	}

	private static int cntHouse(int[] curr, int K) {

		int cnt = 0;
		boolean[][] visited = new boolean[N][N];
		
		for (int dr = -K + 1; dr < K; dr++) {

			for (int dc = -K + 1; dc < K; dc++) {
				
				if(Math.abs(dr) + Math.abs(dc) < K) {
					
					int nr = curr[0] + dr;
					int nc = curr[1] + dc;
					
					if(nr >= 0 && nr <N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == 1) {
						cnt++;
					}
				}
				
			}
			
		}

		return cnt;
	}

}
