import java.io.*;
import java.util.*;

public class Main {
	private static int V, E, R; // 노드, 간선의 갯수, 시작 정점
	private static List<Integer>[] adj;
	private static int[] visited;
	private static StringBuilder sb = new StringBuilder();
	private static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[V+1];
		for(int i = 1; i<=V; i++) {
			adj[i] = new ArrayList<>();
		}

		visited = new int[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);

		}
		
		for(int i = 1; i<=V; i++) {
			Collections.sort(adj[i]);
		}
		
		dfs(R);
		
		for(int i = 1; i<visited.length; i++) {
			sb.append(visited[i]).append("\n");
		}
		System.out.println(sb.toString());

	}

	public static void dfs(int v) {
		visited[v] = ++count;
		
		for(int value : adj[v]) {
			if(visited[value] == 0) {
				dfs(value);
			}
		}
	}

}
