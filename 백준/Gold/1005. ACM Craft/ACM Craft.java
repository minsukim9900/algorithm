import java.io.*;
import java.util.*;

public class Main {

	private static int V, E, end;
	private static int[] time, degree;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			time = new int[V + 1];
			degree = new int[V + 1];

			adj = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				adj[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= V; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adj[from].add(to);
				degree[to]++;

			}

			end = Integer.parseInt(br.readLine());
			
			sb.append(tropological()).append("\n");

		}
		
		
		System.out.println(sb.toString());
	}

	private static int tropological() {
		
		int[] result = new int[V+1];
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i<=V; i++) {
			result[i] = time[i];
			
			if(degree[i] == 0) {
				q.offer(i);
			}
			
		}
		
		
		while(!q.isEmpty()) {
			
			int curr = q.poll();
			
			if(curr == end) {
				
				return result[end];
			}
			
			for(int w : adj[curr]) {
				
				degree[w]--;
				
				result[w] = Math.max(result[w], result[curr]+time[w]);
				
				if(degree[w] == 0) {
					q.offer(w);
				}
				
			}
			
		}
		
		return -1;
	}

}