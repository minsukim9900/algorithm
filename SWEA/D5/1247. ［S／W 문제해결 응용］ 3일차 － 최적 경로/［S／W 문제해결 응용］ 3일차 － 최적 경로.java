import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, answer;
	private static int[][] loc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			answer = Integer.MAX_VALUE;

			loc = new int[N + 2][2];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N + 2; i++) {
				loc[i][0] = Integer.parseInt(st.nextToken());
				loc[i][1] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0, 0, new boolean[N + 2]);
			sb.append("#").append(t).append(" ").append(answer).append("\n");

		}
		System.out.println(sb.toString());
	}

	private static void dfs(int depth, int sum, int pre, boolean[] visited) {
		if (sum >= answer) {
			return;
		}

		if (depth == N) {
			sum += Math.abs(loc[pre][0] - loc[1][0]) + Math.abs(loc[pre][1] - loc[1][1]);
			answer = Math.min(answer, sum);
			return;
		}

		for (int curr = 2; curr < N + 2; curr++) {
			if (visited[curr]) {
				continue;
			}

			int dis = Math.abs(loc[pre][0] - loc[curr][0]) + Math.abs(loc[pre][1] - loc[curr][1]);
			visited[curr] = true;

			dfs(depth + 1, sum + dis, curr, visited);

			visited[curr] = false;
		}
	}
}
