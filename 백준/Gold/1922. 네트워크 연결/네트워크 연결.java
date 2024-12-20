import java.io.*;
import java.util.*;

public class Main {

	private static int[] p;

	private static class Edge implements Comparable<Edge> {
		int x, y, w;

		public Edge(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		p = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		Edge[] edges = new Edge[M];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(x, y, w);
		}

		Arrays.sort(edges);

		int pick = 0;
		int ans = 0;

		for (int i = 0; i < M; i++) {
			int px = findParent(edges[i].x);
			int py = findParent(edges[i].y);

			if (px != py) {
				pick++;
				ans += edges[i].w;
				union(px, py);
			}

			if (pick >= (N - 1)) {
				break;
			}
		}

		System.out.println(ans);

	}

	private static int findParent(int x) {

		if (x != p[x]) {
			p[x] = findParent(p[x]);
		}

		return p[x];
	}

	private static void union(int x, int y) {
		p[y] = x;
	}

}
