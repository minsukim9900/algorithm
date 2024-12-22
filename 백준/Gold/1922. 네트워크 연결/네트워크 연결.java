import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static List<Node>[] adj;
	private static PriorityQueue<Node> pq = new PriorityQueue<>();

	private static class Node implements Comparable<Node> {
		int x, y, w;

		private Node(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", w=" + w + "]";
		}
		
		

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adj = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[x].add(new Node(x, y, w));
			adj[y].add(new Node(y, x, w));
		}
		
		System.out.println(prim(1));

	}
	
	private static int prim(int st) {
		
		boolean[] visited = new boolean[N+1];
		pq.addAll(adj[st]);
		visited[st] = true;
		int pick = 1;
		int ans = 0;
		while(pick != N	) {
			Node node = pq.poll();
			
			if(!visited[node.y]) {
				visited[node.y] =true;
				ans += node.w;
				pq.addAll(adj[node.y]);
				pick++;
			}
			
		}
		
		return ans;
	}

}
