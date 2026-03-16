import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static long[] nums, sumTree, lazyTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N];
		sumTree = new long[N * 4];
		lazyTree = new long[N * 4];

		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		init(1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int queryLeft = Integer.parseInt(st.nextToken()) - 1;
			int queryRight = Integer.parseInt(st.nextToken()) - 1;

			if (order == 1) {
				long target = Long.parseLong(st.nextToken());
				updateQuery(1, 0, N - 1, queryLeft, queryRight, target);
			} else {
				sb.append(sumQuery(1, 0, N - 1, queryLeft, queryRight)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			sumTree[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		init(next, nodeLeft, mid);
		init(next + 1, mid + 1, nodeRight);
		sumTree[node] = sumTree[next] + sumTree[next + 1];
	}

	private static void lazyPropagate(int node, int nodeLeft, int nodeRight) {
		if (lazyTree[node] == 0) {
			return;
		}

		int next = node << 1;

		if (nodeLeft != nodeRight) {
			lazyTree[next] += lazyTree[node];
			lazyTree[next + 1] += lazyTree[node];
		}

		sumTree[node] += lazyTree[node] * (nodeRight - nodeLeft + 1);
		lazyTree[node] = 0;
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight, long target) {
		lazyPropagate(node, nodeLeft, nodeRight);

		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			lazyTree[node] = target;
			lazyPropagate(node, nodeLeft, nodeRight);
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, queryLeft, queryRight, target);
		updateQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight, target);

		sumTree[node] = sumTree[next] + sumTree[next + 1];
	}

	private static long sumQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		lazyPropagate(node, nodeLeft, nodeRight);

		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return sumTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long leftSum = sumQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightSum = sumQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftSum + rightSum;
	}
}