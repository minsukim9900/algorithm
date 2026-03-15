import java.io.*;
import java.util.*;

public class Main {
	private static int N, Q;
	private static int[] nums, maxTree, minTree;
	private static final int INF = 180_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		maxTree = new int[N * 4];
		minTree = new int[N * 4];

		init(1, 0, N - 1);


		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int queryLeft = Integer.parseInt(st.nextToken()) - 1;
			int queryRight = Integer.parseInt(st.nextToken()) - 1;

			int maxNumber = getMaxQuery(1, 0, N - 1, queryLeft, queryRight);
			int minNumber = getMinQuery(1, 0, N - 1, queryLeft, queryRight);
			sb.append(maxNumber - minNumber).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			maxTree[node] = nums[nodeLeft];
			minTree[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		init(next, nodeLeft, mid);
		init(next + 1, mid + 1, nodeRight);

		maxTree[node] = Math.max(maxTree[next], maxTree[next + 1]);
		minTree[node] = Math.min(minTree[next], minTree[next + 1]);
	}

	private static int getMaxQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return Integer.MIN_VALUE;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return maxTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftNumber = getMaxQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightNumber = getMaxQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return Math.max(leftNumber, rightNumber);
	}

	private static int getMinQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return Integer.MAX_VALUE;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return minTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftNumber = getMinQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightNumber = getMinQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return Math.min(leftNumber, rightNumber);
	}
}