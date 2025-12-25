import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static long[] nums;
	private static long[] maxTree, minTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new long[N];
		maxTree = new long[N * 4];
		minTree = new long[N * 4];

		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		init(1, 0, N - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int queryLeft = Integer.parseInt(st.nextToken()) - 1;
			int queryRight = Integer.parseInt(st.nextToken()) - 1;

			sb.append(queryMin(1, 0, N - 1, queryLeft, queryRight)).append("\n");
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

		init(node << 1, nodeLeft, mid);
		init((node << 1) + 1, mid + 1, nodeRight);

		maxTree[node] = Math.max(maxTree[node << 1], maxTree[(node << 1) + 1]);
		minTree[node] = Math.min(minTree[node << 1], minTree[(node << 1) + 1]);
	}

	private static long queryMin(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || queryLeft > nodeRight) {
			return Long.MAX_VALUE;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return minTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		long leftMin = queryMin(node << 1, nodeLeft, mid, queryLeft, queryRight);
		long rightMin = queryMin((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return Math.min(leftMin, rightMin);
	}
}