import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long[] sumTree, countTree;

	private static final long MOD = 1_000_000_007;
	private static final int MAX = 200_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		sumTree = new long[MAX * 4];
		countTree = new long[MAX * 4];

		long answer = 1;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (i != 0) {
				long leftValue = countQuery(1, 0, MAX, 0, num - 1) * num - sumQuery(1, 0, MAX, 0, num - 1);
				long rightValue = sumQuery(1, 0, MAX, num + 1, MAX) - countQuery(1, 0, MAX, num + 1, MAX) * num;
				answer = (leftValue + rightValue) % MOD * answer % MOD;
			}
			updateCountTree(1, 0, MAX, num, 1);
			updateSumTree(1, 0, MAX, num, num);
		}

		System.out.println(answer);
	}

	private static long countQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return countTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long leftCount = countQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightCount = countQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftCount + rightCount;
	}

	private static void updateCountTree(int node, int nodeLeft, int nodeRight, int target, int diff) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		countTree[node] += diff;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateCountTree(next, nodeLeft, mid, target, diff);
		updateCountTree(next + 1, mid + 1, nodeRight, target, diff);
	}

	private static long sumQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return sumTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long leftValue = sumQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightValue = sumQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftValue + rightValue;
	}

	private static void updateSumTree(int node, int nodeLeft, int nodeRight, int target, int diff) {

		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		sumTree[node] += diff;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateSumTree(next, nodeLeft, mid, target, diff);
		updateSumTree(next + 1, mid + 1, nodeRight, target, diff);
	}
}