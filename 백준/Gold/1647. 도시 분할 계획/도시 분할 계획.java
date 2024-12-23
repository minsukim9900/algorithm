import java.io.*;
import java.util.*;

public class Main {
	
	private static int N, M;
	private static boolean[] visited;
	
	private static class Edge implements Comparable<Edge>{
		int x, y, w;

		private Edge(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		
		List<Edge>[] adj = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[x].add(new Edge(x, y, w));
			adj[y].add(new Edge(y, x, w));
		}
		
		System.out.println(prim(1, adj));
		
	}
	
	private static int prim(int st, List<Edge>[] adj) {
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited[st] = true;
		pq.addAll(adj[st]);
		int ans = 0;
		int pick = 1;
		int finW = 0;
		while(pick != N	) {
			Edge e = pq.poll();
			if(!visited[e.y]) {
				visited[e.y] = true;
				ans += e.w;
				pq.addAll(adj[e.y]);
				finW = Math.max(finW, e.w);
				pick++;
			}
		}
		
		return ans - finW;
	}

}
