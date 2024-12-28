import java.io.*;
import java.util.*;

public class Main {

	private static int V, E;
	private static List<Integer>[] adj;
	private static int[] degree;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		degree = new int[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adj[from].add(to);
			degree[to]++;

		}
		
		tropological();
		System.out.println(sb.toString());

	}
	
	private static void tropological() {
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= V; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			sb.append(curr).append(" ");
			
			for(int w : adj[curr]) {
				degree[w]--;
				if(degree[w] == 0) {
					q.add(w);
				}
			}
		}
		
	}

}