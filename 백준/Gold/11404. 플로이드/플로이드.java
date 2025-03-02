import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static final int INF = 987_654_321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		for (int[] m : map) {
			Arrays.fill(m, INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map[from][to] = Math.min(w,  map[from][to]);
		}

		for (int k = 1; k <= N; k++) {

			for (int i = 1; i <= N; i++) {

				if (i == k)
					continue;

				for (int j = 1; j <= N; j++) {

					if (i == j || j == k)
						continue;

					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);

				}

			}

		}

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (map[r][c] >= INF)
					map[r][c] = 0;
				sb.append(map[r][c] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
