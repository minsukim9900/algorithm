import java.io.*;
import java.util.*;

public class Main {

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
			return Integer.compare(this.dist, o.dist);
		}

	}

	private static int N, E;
	private static List<Node>[] adj;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[x].add(new Node(y, w));
		}
		
		visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(dist, start);
		System.out.println(dist[end]);
		
	}

	private static void dijkstra(int[] dist, int start) {
		dist[start] = 0;
		
		PriorityQueue<PqFormat> pq = new PriorityQueue<>();
		pq.offer(new PqFormat(start, dist[start]));
		
		while(!pq.isEmpty()) {
			PqFormat curr = pq.poll();
			
			if(visited[curr.idx]) continue;
			
			visited[curr.idx] = true;
			
			for(Node n : adj[curr.idx]) {
				if(dist[n.y] > dist[curr.idx] + n.w) {
					dist[n.y] = dist[curr.idx] + n.w;
					pq.offer(new PqFormat(n.y, dist[n.y]));
				}
			}
			
			
		}
		
	}

}