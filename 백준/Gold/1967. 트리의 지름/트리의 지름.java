import java.io.*;
import java.util.*;

public class Main {

	private static class Node {
		int link, value;

		private Node(int link, int value) {
			super();
			this.link = link;
			this.value = value;
		}

	}

	private static int N;
	private static int max = -1;
	private static List<Node>[] node;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		node = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			node[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			node[from].add(new Node(to, w));
			node[to].add(new Node(from, w));
		}

		for (int i = 1; i <= N; i++) {

			visited = new boolean[N + 1];
			visited[i] = true;
			dfs(i, 0);

		}
		
		System.out.println(max);
		
	}

	private static void dfs(int start, int value) {

		for (Node n : node[start]) {
			if(!visited[n.link]) {
				visited[n.link] = true;
				dfs(n.link, value+n.value);
			}
		}
		
		max = Math.max(max, value);

	}

}