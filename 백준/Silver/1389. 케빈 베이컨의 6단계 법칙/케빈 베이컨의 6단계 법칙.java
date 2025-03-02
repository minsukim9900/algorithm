import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];

		for (int[] m : map) {
			Arrays.fill(m, INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			map[from][to] = 1;
			map[to][from] = 1;

		}

		// 경 - 출 - 도
		for (int k = 1; k <= N; k++) { // 경유지

			for (int i = 1; i <= N; i++) { // 출발지

				if (i == k)
					continue;

				for (int j = 1; j <= N; j++) { // 도착지

					if (j == k || j == i)
						continue;

					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);

				}
			}

		}

		int[] cnt = new int[N + 1];

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (map[r][c] >= INF)
					map[r][c] = 0;
				cnt[r] += map[r][c];
			}
		}
		
		int min = INF;
		int idx = 0;
		
		for (int i = 1; i <= N; i++) {
			
			if(min > cnt[i]) {
				min = cnt[i];
				idx = i;
			}
			
		}
		
		System.out.println(idx);

	}
}
