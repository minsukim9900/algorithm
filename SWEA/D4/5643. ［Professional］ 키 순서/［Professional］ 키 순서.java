import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M, cnt;
	private static int[][] adj, radj;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adj = new int[N + 1][N + 1];
			radj = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {

				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				radj[to][from] = adj[from][to] = 1;

			}

			int ans = 0;

			for (int i = 1; i <= N; i++) {
				cnt = 0;
				dfs(i, adj, new boolean[N + 1]);
				dfs(i, radj, new boolean[N + 1]);
				if (cnt == N - 1) {
					ans++;
				}
			}

			sb.append("#" + t + " ").append(ans + "\n");

		}
		System.out.println(sb.toString());
	}

	private static void dfs(int st, int[][] map, boolean[] visited) {
		visited[st] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && map[st][i] != 0) {
				dfs(i, map, visited);
				cnt++;
			}
		}

	}

}
