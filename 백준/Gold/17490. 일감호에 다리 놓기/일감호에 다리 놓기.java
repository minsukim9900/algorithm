import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static long K;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		List<Edge> edges = new ArrayList<>();

		p = new int[N + 1];
		int[] S = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			p[i] = i;
			S[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] state = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int min = Math.min(x, y);
			int max = Math.max(y, x);

			if (min == 1 && max == N) {
				state[max] = true;
			} else {
				state[min] = true;
			}
		}

		for (int x = 1; x <= N; x++) {
			if (state[x]) {
				continue;
			}
			int y = (x + 1) > N ? 1 : (x + 1);

			union(findP(x), findP(y));
		}

		int[] minWeight = new int[N + 1];
		Arrays.fill(minWeight, Integer.MAX_VALUE);

		for (int i = 1; i < N + 1; i++) {
			int parent = findP(i);
			minWeight[parent] = Math.min(minWeight[parent], S[i]);
		}

		long sum = 0;
		int components = 0;

		for (int i = 1; i < N + 1; i++) {
			if (p[i] == i) {
				components++;
				sum += minWeight[i];

				if (sum > K) {
					break;
				}
			}

		}

		if (components == 1) {
			System.out.println("YES");
		} else {
			System.out.println(sum <= K ? "YES" : "NO");
		}
	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}
		return p[x];
	}

	private static void union(int x, int y) {
		p[y] = x;
	}

	private static class Edge {
		int x;
		int y;
		int w;

		public Edge(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
}
