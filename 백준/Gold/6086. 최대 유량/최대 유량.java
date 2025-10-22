import java.io.*;
import java.util.*;

public class Main {
	private static int INF = Integer.MAX_VALUE;
	private static int MAX = 52;
	private static int[][] flow, capacity;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		flow = new int[MAX][MAX];
		capacity = new int[MAX][MAX];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = charcterToInt(st.nextToken().charAt(0));
			int y = charcterToInt(st.nextToken().charAt(0));
			int w = Integer.parseInt(st.nextToken());
			
			capacity[x][y] += w;
			capacity[y][x] += w;
		}

		System.out.println(bfs(26, 51));
	}

	private static int bfs(int start, int end) {
		int answer = 0;
		int[] p = new int[MAX];
		Queue<Integer> q;

		while (true) {
			Arrays.fill(p, -1);
			q = new ArrayDeque<>();

			p[start] = start;
			q.add(start);

			while (!q.isEmpty() && p[end] == -1) {
				int curr = q.poll();

				for (int next = 0; next < MAX; next++) {
					if (capacity[curr][next] - flow[curr][next] > 0 && p[next] == -1) {
						q.add(next);
						p[next] = curr;
					}
				}
			}

			if (p[end] == -1)
				break;

			int amount = Integer.MAX_VALUE;
			for (int i = end; i != start; i = p[i]) {
				amount = Math.min(capacity[p[i]][i] - flow[p[i]][i], amount);
			}

			for (int i = end; i != start; i = p[i]) {
				flow[p[i]][i] += amount;
				flow[i][p[i]] -= amount;
			}

			answer += amount;
		}

		return answer;
	}

	private static int charcterToInt(char c) {
		if (c >= 'a' && c <= 'z') {
			return c - 'a';
		}

		return c - 'A' + 26;
	}
}
