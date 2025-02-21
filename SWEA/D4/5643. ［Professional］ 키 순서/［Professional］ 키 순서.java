import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M, cnt;
	private static int[][] adj;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adj = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {

				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from][to] = 1;

			}

			for (int i = 1; i <= N; i++) {
				adj[i][0] = -1;
			}

			for (int i = 1; i <= N; i++) {
				if (adj[i][0] != -1)
					continue;
				gtDFS(i);
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (adj[i][j] == 1)
						adj[0][j]++;
				}
			}

			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (adj[0][i] + adj[i][0] == N - 1)
					ans++;
			}

			sb.append("#" + t + " ").append(ans + "\n");

		}
		System.out.println(sb.toString());
	}

	private static void gtDFS(int curr) {
		for (int i = 1; i <= N; i++) {

			if (adj[curr][i] == 0)
				continue;

			if (adj[i][0] == -1) {
				gtDFS(i);
			}
			
			if(adj[i][0] > 0) {
				for (int j = 1; j <= N; j++) {
					if (adj[i][j] == 1)
						adj[curr][j] = 1;
				}
			}

		}

		adj[curr][0] = 0;
		for (int k = 1; k <= N; k++) {
			adj[curr][0] += adj[curr][k];
		}

	}

}
