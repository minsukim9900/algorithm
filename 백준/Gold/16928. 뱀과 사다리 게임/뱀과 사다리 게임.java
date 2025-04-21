import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] board = new int[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			board[from] = to;
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 1, 0 });
		boolean[] visited = new boolean[101];
		visited[1] = true;
		int rs = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (curr[0] == 100) {
				rs = curr[1];
				break;
			}

			for (int i = 1; i <= 6; i++) {
				int next = curr[0] + i;

				if (next <= 100 && !visited[next]) {
					if (board[next] != 0) {
						next = board[next];
					}
					visited[next] = true;
					q.add(new int[] { next, curr[1] + 1 });
				}
			}

		}

		return rs;
	}
}