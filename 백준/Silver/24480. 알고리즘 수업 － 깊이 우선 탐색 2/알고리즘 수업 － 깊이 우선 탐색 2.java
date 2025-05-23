import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, R;
	private static List<Integer>[] adj;
	private static int[] visited;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		visited = new int[N+1];
		adj = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		
		for(int i = 1; i<=N; i++) {
			Collections.sort(adj[i],Collections.reverseOrder());
		}
		
		dfs(R);
		
		for(int i = 1; i<=N; i++) {
			sb.append(visited[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void dfs(int v) {
		visited[v] = ++cnt;
		
		for(int w : adj[v]) {
			if(visited[w] == 0) {
				dfs(w);
			}
		}
	}

}
