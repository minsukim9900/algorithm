import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] adj;
	private static int[] in;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		in = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int pre = Integer.parseInt(st.nextToken());
			for (int c = 1; c < cnt; c++) {
				int x = Integer.parseInt(st.nextToken());
				adj[pre].add(x);
				pre = x;
				in[x]++;
			}
		}

		System.out.println(bfs());
	}

	private static String bfs() {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			if (in[i] == 0) {
				q.add(i);
			}
		}
		
		int count = 0;
		while (!q.isEmpty()) {
			int curr = q.poll();
			count++;
			sb.append(curr).append("\n");

			for (int next : adj[curr]) {
				if (--in[next] == 0) {
					q.add(next);
				}
			}
		}
		
		return count == N ? sb.toString() : "0";
	}
}