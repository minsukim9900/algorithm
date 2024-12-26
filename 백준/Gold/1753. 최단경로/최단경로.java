import java.io.*;
import java.util.*;

public class Main {

	private static class Node{

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

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[V+1];
		
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		List<Node>[] adj = new ArrayList[V+1];
		for(int i = 1; i<=V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int start = Integer.parseInt(br.readLine());
		
		for(int i = 0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[x].add(new Node(y, w));
		}
		
		dijkstra(start, visited, adj, dist, V, E);
		
		for(int i = 1; i<=V; i++) {
			if(visited[i]) {
				sb.append(dist[i]).append("\n");
			}else {
				sb.append("INF").append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	private static void dijkstra(int start, boolean[] visited,
			List<Node>[] adj, int[] dist, int V, int E) {
		PriorityQueue<PqFormat> pq = new PriorityQueue<>();
		
		pq.add(new PqFormat(start, 0));
		dist[start] = 0;
		
		
		while(!pq.isEmpty()) {
			PqFormat info = pq.poll();
			
			if(visited[info.idx]) continue;
			
			visited[info.idx] = true;
			
			
			for(Node n : adj[info.idx]) {
				if(dist[n.y] > dist[info.idx] + n.w) {
					dist[n.y] = dist[info.idx] + n.w;
					pq.offer(new PqFormat(n.y, dist[n.y]));
				}
			}
			
			
		}
		
	}

}