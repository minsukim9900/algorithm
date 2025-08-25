import java.io.*;
import java.util.*;

public class Main {

	private static class Planet {
		int idx;
		int x, y, z;

		public Planet(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Planet [idx=" + idx + ", x=" + x + ", y=" + y + ", z=" + z + "]";
		}

	}

	private static class Node {
		int from, to, w;

		public Node(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", w=" + w + "]";
		}

	}

	private static int[] p;
	private static PriorityQueue<Planet>[] pq = new PriorityQueue[3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		pq[0] = new PriorityQueue<>((a, b) -> a.x - b.x);
		pq[1] = new PriorityQueue<>((a, b) -> a.y - b.y);
		pq[2] = new PriorityQueue<>((a, b) -> a.z - b.z);

		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			Planet planet = new Planet(i, x, y, z);
			pq[0].add(planet);
			pq[1].add(planet);
			pq[2].add(planet);
		}

		PriorityQueue<Node> edges = new PriorityQueue<>((a, b) -> a.w - b.w);

		for (int i = 0; i < 3; i++) {
			Planet compA = pq[i].poll();

			while (!pq[i].isEmpty()) {
				Planet compB = pq[i].poll();
				int w = 0;

				if (i == 0) {
					w = Math.abs(compA.x - compB.x);
				} else if (i == 1) {
					w = Math.abs(compA.y - compB.y);
				} else {
					w = Math.abs(compA.z - compB.z);
				}
				edges.add(new Node(compA.idx, compB.idx, w));
				compA = compB;
			}
		}

		System.out.println(kruskal(N, edges));
	}

	private static int kruskal(int N, PriorityQueue<Node> edges) {
		int pick = 0;
		int ans = 0;
		while (!edges.isEmpty()) {
			Node A = edges.poll();

			int px = findP(A.from);
			int py = findP(A.to);

			if (px != py) {
				pick++;
				ans += A.w;
				union(px, py);
			}

			if (pick == N - 1) {
				break;
			}
		}
		return ans;
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