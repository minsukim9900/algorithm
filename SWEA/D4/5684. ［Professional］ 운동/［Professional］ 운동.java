import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M;

	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int[][] dist = new int[N + 1][N + 1];

			for (int node = 1; node < N + 1; node++) {
				Arrays.fill(dist[node], INF);
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				dist[s][e] = Math.min(dist[s][e], c);
			}

			for (int k = 1; k < N + 1; k++) {
				for (int i = 1; i < N + 1; i++) {
					if (i == k) {
						continue;
					}

					for (int j = 1; j < N + 1; j++) {
						if (j == k) {
							continue;
						}

						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}

			int result = INF;

			for (int node = 1; node < N + 1; node++) {
				result = Math.min(result, dist[node][node]);
			}

			result = result == INF ? -1 : result;

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
	}
}
