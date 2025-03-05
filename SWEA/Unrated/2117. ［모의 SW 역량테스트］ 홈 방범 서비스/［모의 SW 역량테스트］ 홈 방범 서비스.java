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
			for (int k = 1; k <= 21; k++) {

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						int cnt = cntHouse(new int[] { r, c }, k);
						int tmp = M * cnt -  price[k];
						
						if(tmp >= 0) {
							if(max < cnt) {
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

		for (int i = 0; i < K; i++) {

			int nr = curr[0] - i;
			if (nr < 0)
				continue;
			cnt += map[nr][curr[1]];

			for (int j = K - i - 1; j > 0; j--) {
				for (int idx = 0; idx < 2; idx++) {
					int nc = curr[1] + (delta[idx][1] * j);
					if (nc >= 0 && nc < N && map[nr][nc] == 1) {
						cnt++;
					}
				}
			}

		}

		for (int i = 1; i <= K - 1; i++) {

			int nr = curr[0] + i;
			if (nr >= N)
				continue;
			cnt += map[nr][curr[1]];

			for (int j = K - i - 1; j > 0; j--) {
				for (int idx = 0; idx < 2; idx++) {
					int nc = curr[1] + (delta[idx][1] * j);
					if (nc >= 0 && nc < N && map[nr][nc] == 1) {
						cnt++;
					}

				}

			}

		}

		return cnt;
	}

}
