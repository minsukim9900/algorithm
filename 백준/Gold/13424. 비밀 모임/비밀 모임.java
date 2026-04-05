import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] dist;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			dist = new int[N + 1][N + 1];
			for (int r = 1; r < N + 1; r++) {
				for (int c = 1; c < N + 1; c++) {
					dist[r][c] = INF;

					if (r == c) {
						dist[r][c] = 0;
					}
				}
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				dist[x][y] = w;
				dist[y][x] = w;
			}

			for (int k = 1; k < N + 1; k++) {
				for (int i = 1; i < N + 1; i++) {
					if (i == k)
						continue;
					for (int j = 1; j < N + 1; j++) {
						if (j == i || j == k)
							continue;

						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}

			K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int[] friends = new int[K];
			for (int i = 0; i < K; i++) {
				friends[i] = Integer.parseInt(st.nextToken());
			}

			int maxDist = INF;
			int answer = 0;

			for (int i = 1; i < N + 1; i++) {
				int sum = 0;

				for (int friend : friends) {
					sum += dist[friend][i];
				}

				if (maxDist > sum) {
					maxDist = sum;
					answer = i;
				}
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}