import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		int[] dist = new int[1_000_001];
		Arrays.fill(dist, -1);

		Queue<int[]> q = new ArrayDeque<>();
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			dist[num] = 0;
			q.add(new int[] { num, dist[num] });
		}
		System.out.println(simulate(q, dist));
	}

	private static int simulate(Queue<int[]> q, int[] dist) {
		int answer = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int num = curr[0];
			int d = curr[1];
			answer = Math.max(answer, d);

			for (int i = 0; i <= 19; i++) {
				int next = num ^ (1 << i);

				if (next <= N && dist[next] == -1) {
					dist[next] = d + 1;
					q.add(new int[] { next, d + 1 });
				}
			}
		}
		return answer;
	}
}
