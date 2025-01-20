import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, R;
	private static List<Integer>[] adj;
	private static int[] result;
	private static int depth = 1;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		result = new int[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adj[from].add(to);
			adj[to].add(from);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adj[i]);
		}
		
		result[R] = depth;
		dfs(R);

		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append("\n");
		}

		System.out.println(sb.toString());

	}

	private static void dfs(int V) {
		
		
		for(int w : adj[V]) {
			if(result[w] == 0) {
				result[w] = ++depth;
				dfs(w);
			}
		}

	}

}