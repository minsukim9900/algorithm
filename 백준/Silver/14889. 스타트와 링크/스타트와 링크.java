import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] link;
	private static int min = Integer.MAX_VALUE;
	private static boolean[] visited;
	private static int[] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		link = new int[N][N];
		visited = new boolean[N];
		result = new int[N/2];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				link[r][c] = Integer.parseInt(st.nextToken());
			}

		}

		dfs(0, 0);
		System.out.println(min);
	}

	private static void dfs(int num, int depth) {

		if (depth == N / 2) {
			calS(result);
		} else {

			for (int i = num; i < N; i++) {
				if (visited[i])
					continue;
				visited[i] = true;
				result[depth] = i;
				dfs(i + 1, depth + 1);
				visited[i] = false;

			}

		}
	}

	private static void calS(int[] tmp) {

		int start_team = 0;
		int link_team = 0;

		for (int i = 0; i < N - 1; i++) {
			
			for (int j = i+1; j < N; j++) {

				if (visited[i] && visited[j]) {
					start_team += link[i][j];
					start_team += link[j][i];
				}

				if (!visited[i] && !visited[j]) {
					link_team += link[i][j];
					link_team += link[j][i];
				}
			}
		}
		
		int diff = Math.abs(start_team-link_team);
		min = Math.min(min, diff);
	}

}
