import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[] nums;
	private static long[] multiTree;
	private static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N];
		multiTree = new long[N * 4];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		init(1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if (a == 1) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				long c = Integer.parseInt(st.nextToken());
				update(1, 0, N - 1, b, c);
			} else {
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				sb.append(queryMulti(1, 0, N - 1, b, c)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			multiTree[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		init(node << 1, nodeLeft, mid);
		init((node << 1) + 1, mid + 1, nodeRight);

		multiTree[node] = multiTree[node << 1] * multiTree[(node << 1) + 1] % MOD;
	}

	private static void update(int node, int nodeLeft, int nodeRight, int queryIndex, long value) {
		if (nodeRight < queryIndex || nodeLeft > queryIndex) {
			return;
		}

		if (nodeLeft == nodeRight) {
			multiTree[node] = value % MOD;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		update(node << 1, nodeLeft, mid, queryIndex, value);
		update((node << 1) + 1, mid + 1, nodeRight, queryIndex, value);

		multiTree[node] = multiTree[node << 1] * multiTree[(node << 1) + 1] % MOD;
	}

	private static long queryMulti(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (nodeRight < queryLeft || queryRight < nodeLeft) {
			return 1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return multiTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		long multiLeft = queryMulti(node << 1, nodeLeft, mid, queryLeft, queryRight) % MOD;
		long multiRight = queryMulti((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight) % MOD;
		return multiLeft * multiRight % MOD;
	}
}