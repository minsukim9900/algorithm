import java.io.*;
import java.util.*;

public class Main {

	private static class Edge implements Comparable<Edge> {
		int from, to, w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	private static int N, M;
	private static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0) break;
			
			p = new int[N + 1];
			
			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}
			
			Edge[] edges = new Edge[M];
			int sum = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				sum += w;
				
				edges[i] = new Edge(from, to, w);
			}
			
			Arrays.sort(edges);
			
			int pick = 0;
			
			for (int i = 0; i < M; i++) {
				
				int px = findP(edges[i].from);
				int py = findP(edges[i].to);
				
				if (px != py) {
					union(px, py);
					sum -= edges[i].w;
				}
				
				if(pick == N-1) break;
				
			}
			
			System.out.println(sum);
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

}