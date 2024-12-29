import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] time;
	private static int[] degree;
	private static List<Integer>[] adj;
	private static int result = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		time = new int[N + 1];
		degree = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			time[i] = t;
			degree[i] = p;

			for (int j = 0; j < p; j++) {
				int from = Integer.parseInt(st.nextToken());
				adj[from].add(i);
			}

		}

		System.out.println(tropological());

	}

	private static int tropological() {
		Queue<Integer> q = new ArrayDeque<>();

		int[] result = new int[N + 1];

		for (int i = 1; i <= N; i++) {

			result[i] = time[i];

			if (degree[i] == 0) {
				q.add(i);
			}
		}


		while (!q.isEmpty()) {
			int curr = q.poll();

			int maxTime = 0;

			for (int i = 0; i < adj[curr].size(); i++) {
				int next = adj[curr].get(i);

				degree[next]--;

				result[next] = Math.max(result[next], result[curr] + time[next]);

				if (degree[next] == 0) {
					q.add(next);
				}

			}

		}

		int max = 0;
		
		for(int n : result) {
			max = Math.max(max, n);
		}
		
		return max;
	}

}