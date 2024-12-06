import java.io.*;
import java.util.*;

public class Main {

	private static int N, x, y, M;
	private static StringBuilder sb = new StringBuilder();
	private static List<Integer>[] adj;
	private static boolean[] visited;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[p].add(c);
			adj[c].add(p);
		}
		
		dfs(x,0);
		if(result == 0) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}

	}

	private static void dfs(int num, int depth) {
		if (num == y) {
			result = depth;
		} else {
			if (visited[num])
				return;
			else {
				visited[num] = true;
				for (int i = 0; i < adj[num].size(); i++) {
					dfs(adj[num].get(i), depth+1);
				}
			}

		}
	}
}
