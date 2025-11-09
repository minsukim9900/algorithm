import java.io.*;
import java.util.*;

public class Main {
	private static int MAX = 100_001;
	private static int N, K;
	private static int[] delta = { -1, 1, 2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { N, 0 });
		int answer = 0;
		boolean[] visited = new boolean[MAX];
		visited[N] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int d = curr[1];
			
			if(x == K) {
				answer = d;
				break;
			}
			
			for (int i = 0; i < 3; i++) {
				int nx = x * delta[i];
				if (i < 2) {
					nx = x + delta[i];
				}

				if (isRange(nx) && !visited[nx]) {
					visited[nx] = true;
					q.add(new int[] { nx, d + 1 });
				}
			}
		}
		return answer;
	}

	private static boolean isRange(int x) {
		return x >= 0 && x < MAX;
	}
}