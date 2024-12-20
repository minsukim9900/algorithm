import java.io.*;
import java.util.*;

public class Main {

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

	private static int[] p;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		p = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}

		Edge[] edges = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(x, y, w);
		}

		Arrays.sort(edges);
		
		long ans = 0;
		int pick = 0;

		for (int i = 0; i < E; i++) {
			int px = findP(edges[i].x);
			int py = findP(edges[i].y);
			if(px != py) {
				union(px, py);
				ans += edges[i].w;
				pick++;
			}
			
			if(pick >= V-1) {
				break;
			}
		}
		
		System.out.println(ans);

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

}
