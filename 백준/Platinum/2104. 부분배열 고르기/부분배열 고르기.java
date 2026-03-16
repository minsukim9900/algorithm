import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] indexTree, nums;
	private static long[] sumTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		indexTree = new int[N * 4];
		sumTree = new long[N * 4];
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		init(1, 0, N - 1);
		System.out.println(calMaxScore(0, N - 1));
	}

	private static long calMaxScore(int queryLeft, int queryRight) {
		long sum = sumQuery(1, 0, N - 1, queryLeft, queryRight);
		int minValueIndex = minIndexQuery(1, 0, N - 1, queryLeft, queryRight);
		int minValue = nums[minValueIndex];
		long result = sum * minValue;

		long leftResult = 0L;
		long rightResult = 0L;
		if (minValueIndex - 1 >= queryLeft) {
			leftResult = calMaxScore(queryLeft, minValueIndex - 1);
		}

		if (queryRight >= minValueIndex + 1) {
			rightResult = calMaxScore(minValueIndex + 1, queryRight);
		}

		return Math.max(result, Math.max(leftResult, rightResult));
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			sumTree[node] = nums[nodeLeft];
			indexTree[node] = nodeLeft;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		init(next, nodeLeft, mid);
		init(next + 1, mid + 1, nodeRight);
		sumTree[node] = sumTree[next] + sumTree[next + 1];
		indexTree[node] = nums[indexTree[next]] <= nums[indexTree[next + 1]] ? indexTree[next] : indexTree[next + 1];
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

	private static int minIndexQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return -1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return indexTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftIndex = minIndexQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightIndex = minIndexQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		if (leftIndex == -1) {
			return rightIndex;
		} else if (rightIndex == -1) {
			return leftIndex;
		}

		return nums[leftIndex] <= nums[rightIndex] ? leftIndex : rightIndex;
	}
}