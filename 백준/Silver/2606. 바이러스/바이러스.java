import java.io.*;
import java.util.*;

public class Main {
	
	private static int V, E;
	private static List<Integer>[] adj;
	private static boolean[] visited;
	private static int cnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[V+1];
		visited	= new boolean[V+1];
		for(int i = 1; i<=V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		
		for(int i = 0; i<E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);
			
		}
		
		dfs(1);
		cnt--;
		System.out.println(cnt);
	}
	
	public static void dfs(int v) {
		cnt++;
		visited[v] = true;
		
		for(int curr : adj[v]) {
			if(!visited[curr])
				dfs(curr);
		}
	}
}
