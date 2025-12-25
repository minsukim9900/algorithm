import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static long[] nums;
	private static long[] sumTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N];
		sumTree = new long[4 * N];

		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		init(1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if ((a & 1) == 1) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				long c = Long.parseLong(st.nextToken());
				update(1, 0, N - 1, b, c);
			} else {
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				sb.append(querySum(1, 0, N - 1, b, c)).append("\n");
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

		init(node * 2, nodeLeft, mid);
		init(node * 2 + 1, mid + 1, nodeRight);

		sumTree[node] = sumTree[node * 2] + sumTree[node * 2 + 1];
	}

	private static void update(int node, int nodeLeft, int nodeRight, int queryIndex, long value) {
		if (queryIndex < nodeLeft || nodeRight < queryIndex) {
			return;
		}

		if (nodeLeft == nodeRight) {
			sumTree[node] = value;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		update(node << 1, nodeLeft, mid, queryIndex, value);
		update((node << 1) + 1, mid + 1, nodeRight, queryIndex, value);
		sumTree[node] = sumTree[node << 1] + sumTree[(node << 1) + 1];
	}

	private static long querySum(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || queryLeft > nodeRight) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return sumTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		long leftSum = querySum(node << 1, nodeLeft, mid, queryLeft, queryRight);
		long rightSum = querySum((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return leftSum + rightSum;
	}
}