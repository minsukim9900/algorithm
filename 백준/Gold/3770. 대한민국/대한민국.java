import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static List<Integer>[] eastCity, westCity;

	private static final int MAX = 1000;
	private static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			eastCity = new ArrayList[N + 1];
			for (int i = 1; i < N + 1; i++) {
				eastCity[i] = new ArrayList<>();
			}

			westCity = new ArrayList[M + 1];
			for (int i = 1; i < M + 1; i++) {
				westCity[i] = new ArrayList<>();
			}

			tree = new long[MAX * 4];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int east = Integer.parseInt(st.nextToken());
				int west = Integer.parseInt(st.nextToken());

				eastCity[east].add(west);
			}

			long answer = 0L;
			for (int i = 1; i < N + 1; i++) {
				for (int west : eastCity[i]) {
					answer += countQuery(1, 1, MAX, west + 1, MAX);
				}
				for (int west : eastCity[i]) {
					updateQuery(1, 1, MAX, west, 1);
				}
			}
			sb.append("Test case ").append(t).append(": ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
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

		long leftCount = countQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightCount = countQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return leftCount + rightCount;
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
}