import java.io.*;
import java.util.*;

public class Main {
	private static int N, D;
	private static long[] trees;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		trees = new long[N * 4];

		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		updateQuery(1, 0, N - 1, 0, nums[0]);

		for (int i = 1; i < N; i++) {
			long value = getQuery(1, 0, N - 1, Math.max(0, i - D), i - 1) + nums[i];
			updateQuery(1, 0, N - 1, i, value);
		}

		System.out.println(trees[1]);
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int queryIndex, long value) {
		if (queryIndex < nodeLeft || nodeRight < queryIndex) {
			return;
		}

		if (nodeLeft == nodeRight) {
			trees[node] = value;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, queryIndex, value);
		updateQuery(next + 1, mid + 1, nodeRight, queryIndex, value);

		trees[node] = Math.max(trees[next], trees[next + 1]);
	}

	private static long getQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return trees[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long leftValue = getQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightValue = getQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return Math.max(leftValue, rightValue);
	}
}