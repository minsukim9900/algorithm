import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;

	private static class Node {
		int y, w;

		private Node(int y, int w) {
			super();
			this.y = y;
			this.w = w;
		}

	}

	private static class PqFormat implements Comparable<PqFormat> {
		int idx, dist;

		private PqFormat(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(PqFormat o) {
			return this.dist - o.dist;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		List<Node>[] adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[x].add(new Node(y, w));
			adj[y].add(new Node(x, w));
		}
		
		System.out.println(prim(adj, 1));

	}

	private static int prim(List<Node>[] adj, int start) {

		PriorityQueue<PqFormat> pq = new PriorityQueue<>();
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.add(new PqFormat(start, dist[start]));

		int ans = 0;
		while (!pq.isEmpty()) {
			PqFormat curr = pq.poll();
			
			for(Node node : adj[curr.idx]) {
				
				if(dist[node.y] > dist[curr.idx] + node.w) {
					dist[node.y] = dist[curr.idx] + node.w;
					pq.add(new PqFormat(node.y, dist[node.y]));
				}
				
				
			}
		}
		
		return dist[N];

	}

}