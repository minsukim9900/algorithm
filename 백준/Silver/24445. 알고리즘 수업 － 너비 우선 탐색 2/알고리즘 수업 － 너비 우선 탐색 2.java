import java.io.*;
import java.util.*;

public class Main {
	
	private static int N, M, R;
	private static List<Integer>[] adj;
	private static int[] order;
	private static int cnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		order = new int[N+1];
		
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
			Collections.sort(adj[i], Collections.reverseOrder());
		}
		
		bfs(R);
		
		for(int i = 1; i<=N; i++) {
			sb.append(order[i]).append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	}
	
	public static void bfs(int R) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(R);
		order[R] = ++cnt;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for(int w : adj[curr]) {
				if(order[w] == 0) {
					q.add(w);
					order[w] = ++cnt;
				}
			}
		}
	}
	
	
	
	
	
}
