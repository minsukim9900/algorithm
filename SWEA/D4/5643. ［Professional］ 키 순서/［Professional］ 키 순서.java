import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M;
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

			int ans = 0;

			for (int i = 1; i <= N; i++) {
				int sum = (bsBfs(i) + lsBfs(i));
				if (sum == N - 1) {
					ans++;
				}

			}

			sb.append("#" + t + " ").append(ans + "\n");

		}
		System.out.println(sb.toString());
	}

	private static int bsBfs(int st) {
		boolean[] visited = new boolean[N + 1];
		visited[st] = true;
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(st);

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adj[curr][i] != 0) {
					visited[i] = true;
					cnt++;
					q.offer(i);
				}
			}
		}

		return cnt;
	}

	private static int lsBfs(int st) {
		boolean[] visited = new boolean[N + 1];
		visited[st] = true;
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(st);

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adj[i][curr] != 0) {
					visited[i] = true;
					cnt++;
					q.offer(i);
				}
			}
		}

		return cnt;
	}

}
