import java.io.*;
import java.util.*;

public class Main {
	
	private static int N, M, R;
	private static List<Integer>[] adj;
	private static long[] depth;
	private static long[] visited;
	private static int cnt= 0;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		depth = new long[N+1];
		visited = new long[N+1];
		
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);
		}
		
		for(int i = 1; i<=N; i++) {
			Collections.sort(adj[i]);
		}
		
		bfs(R);
		
		output();
		
		
	}
	
	public static void bfs(int R) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {R, 0});
		depth[R] = 0;
		visited[R] = ++cnt;
		
		
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			for(int w : adj[curr[0]]) {
				if(depth[w] == 0 && w != R) {
					q.add(new int[] {w, curr[1]+1});
					depth[w] = curr[1]+1;
					visited[w] = ++cnt;
				}
			}
		}
	}
	
	
	public static void output() {
		long sum = 0;
		for(int i = 1; i<=N; i++) {
			if(depth[i] == 0 && i != R) {
				depth[i] = -1;
			}
			sum += (depth[i]*visited[i]);
		}
		
		System.out.println(sum);
	}
	
	
}
