import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static final int MAX = 2000;
	private static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = read();
		M = read();

		List<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		tree = new int[MAX * 4];

		for (int i = 0; i < M; i++) {
			int from = read();
			int to = read();

			adj[from].add(to);
		}

		long answer = 0L;
		for (int i = 1; i < N + 1; i++) {

			for (int value : adj[i]) {
				answer += countQuery(1, 1, MAX, value + 1, MAX);
			}

			for (int value : adj[i]) {
				updateQuery(1, 1, MAX, value, 1);
			}
		}

		System.out.println(answer);
	}

	private static long countQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return tree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long left = countQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long right = countQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return left + right;
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target, int diff) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		tree[node] += diff;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target, diff);
		updateQuery(next + 1, mid + 1, nodeRight, target, diff);
	}

	private static int read() throws IOException {
		int n = 0;
		int i;
		while ((i = System.in.read()) >= '0') {
			n = (n << 3) + (n << 1) + (i & 15);
		}
		return n;
	}
}