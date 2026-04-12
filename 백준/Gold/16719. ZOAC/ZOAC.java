import java.io.*;
import java.util.*;

public class Main {
	private static char[] str;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		str = br.readLine().toCharArray();
		adj = new ArrayList[str.length + 1];

		for (int i = 0; i < str.length + 1; i++) {
			adj[i] = new ArrayList<>();

			for (int j = i + 1; j < str.length + 1; j++) {
				adj[i].add(j);
			}
		}

		for (int i = 0; i < str.length + 1; i++) {
			adj[i].sort((a, b) -> Integer.compare(str[a - 1], str[b - 1]));
		}

		boolean[] visited = new boolean[str.length + 1];
		TreeSet<Integer> ts = new TreeSet<>();

		for (int i = 0; i < str.length; i++) {
			if (visited[adj[0].get(i)])
				continue;

			search(adj[0].get(i), visited, ts, sb);
		}
		System.out.println(sb.toString());
	}

	private static void search(int start, boolean[] visited, TreeSet<Integer> ts, StringBuilder sb) {
		visited[start] = true;
		ts.add(start);

		for (int x : ts) {
			sb.append(str[x - 1]);
		}
		sb.append("\n");

		for (int i = 0; i < adj[start].size(); i++) {
			if (visited[adj[start].get(i)])
				continue;

			search(adj[start].get(i), visited, ts, sb);
		}
	}
}