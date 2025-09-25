import java.io.*;
import java.util.*;

public class Main {
	private static boolean isCycle;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = 1;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			if (N == 0)
				break;

			List<Integer>[] adj = new ArrayList[N + 1];

			for (int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				adj[x].add(y);
				adj[y].add(x);
			}


			boolean[] visited = new boolean[N + 1];
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i])
					continue;
				dfs(i, 0, visited, adj);
				if(!isCycle) cnt++;
				isCycle = false;
			}
			
			if (cnt == 1) {
				sb.append(output2(t++)).append("\n");
			} else if(cnt > 1) {
				sb.append(output1(t++, cnt)).append("\n");
			} else {
				sb.append(output3(t++)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int node, int parent, boolean[] visited, List<Integer>[] adj) {
		visited[node] = true;
		
		for (int next : adj[node]) {
			if (next == parent) {
				continue;
			}
			
			if(visited[next]) {
				isCycle = true;
				continue;
			}
			dfs(next, node, visited, adj);
		}
	}

	private static String output1(int t, int cnt) {
		return "Case " + t + ": A forest of " + cnt + " trees.";
	}

	private static String output2(int t) {
		return "Case " + t + ": There is one tree.";
	}

	private static String output3(int t) {
		return "Case " + t + ": No trees.";
	}
}