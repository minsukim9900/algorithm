import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static Map<String, Integer> personDatabase;
	private static String[] names;
	private static int[] out;
	private static List<Integer>[] adj;
	private static Node[] nodes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int idx = 0;
		personDatabase = new HashMap<>();
		names = new String[N];
		out = new int[N];

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		nodes = new Node[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			String name = st.nextToken();
			nodes[i] = new Node(name);
			personDatabase.put(name, idx);
			names[idx] = name;
			idx++;
		}

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parents = st.nextToken();

			int childIndex = personDatabase.get(child);
			int parentsIndex = personDatabase.get(parents);

			out[childIndex]++;
			adj[parentsIndex].add(childIndex);
		}

		List<Integer> info = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (out[i] == 0) {
				info.add(i);
			}
		}
		sb.append(info.size()).append("\n");
		info.sort((a, b) -> names[a].compareTo(names[b]));
		for (int parents : info) {
			sb.append(names[parents]).append(" ");
			makeTree(parents);
		}
		sb.append("\n");
		Arrays.sort(nodes, (a, b) -> a.name.compareTo(b.name));
		for (Node node : nodes) {
			node.sort();
			sb.append(node.name).append(" ");
			sb.append(node.count).append(" ");
			for(String childName : node.child) {
				sb.append(childName).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void makeTree(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);

		while (!q.isEmpty()) {
			int parents = q.poll();

			for (int child : adj[parents]) {
				out[child]--;

				if (out[child] == 0) {
					q.add(child);
					nodes[parents].count++;
					nodes[parents].child.add(names[child]);
				}
			}
		}
	}

	private static class Node {
		String name;
		int count;
		List<String> child;

		public Node(String name) {
			this.name = name;
			this.count = 0;
			this.child = new ArrayList<>();
		}

		public void sort() {
			this.child.sort((a, b) -> a.compareTo(b));
		}
	}
}