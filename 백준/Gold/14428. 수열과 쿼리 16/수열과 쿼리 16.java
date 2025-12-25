import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static long[] nums;
	private static int[] indexTree;
	private static long[] minTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		nums = new long[N];
		minTree = new long[N * 4];
		indexTree = new int[N * 4];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if ((q & 1) == 1) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken());
				updateQuery(1, 0, N - 1, a, b);
			} else {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				sb.append(findIndex(1, 0, N - 1, a, b) + 1).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			minTree[node] = nums[nodeLeft];
			indexTree[node] = nodeLeft;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		init(node << 1, nodeLeft, mid);
		init((node << 1) + 1, mid + 1, nodeRight);

		long leftMinValue = minTree[node << 1];
		int leftIndex = indexTree[node << 1];

		long rightMinValue = minTree[(node << 1) + 1];
		int rightIndex = indexTree[(node << 1) + 1];

		if (leftMinValue == rightMinValue) {
			minTree[node] = leftMinValue;
			indexTree[node] = Math.min(leftIndex, rightIndex);
		} else if (leftMinValue < rightMinValue) {
			minTree[node] = leftMinValue;
			indexTree[node] = leftIndex;
		} else {
			minTree[node] = rightMinValue;
			indexTree[node] = rightIndex;
		}
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int queryIndex, int value) {
		if (nodeLeft > queryIndex || nodeRight < queryIndex) {
			return;
		}

		if (nodeLeft == nodeRight) {
			nums[queryIndex] = value;
			minTree[node] = value;
			indexTree[node] = nodeLeft;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		updateQuery(node << 1, nodeLeft, mid, queryIndex, value);
		updateQuery((node << 1) + 1, mid + 1, nodeRight, queryIndex, value);

		long leftMinValue = minTree[node << 1];
		int leftIndex = indexTree[node << 1];

		long rightMinValue = minTree[(node << 1) + 1];
		int rightIndex = indexTree[(node << 1) + 1];

		if (leftMinValue == rightMinValue) {
			minTree[node] = leftMinValue;
			indexTree[node] = Math.min(leftIndex, rightIndex);
		} else if (leftMinValue < rightMinValue) {
			minTree[node] = leftMinValue;
			indexTree[node] = leftIndex;
		} else {
			minTree[node] = rightMinValue;
			indexTree[node] = rightIndex;
		}
	}

	private static int findIndex(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {

		if (nodeLeft > queryRight || nodeRight < queryLeft) {
			return -1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return indexTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		int leftIndex = findIndex(node << 1, nodeLeft, mid, queryLeft, queryRight);
		int rightIndex = findIndex((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight);

		if (leftIndex == -1) {
			return rightIndex;
		}

		if (rightIndex == -1) {
			return leftIndex;
		}

		if (nums[leftIndex] < nums[rightIndex]) {
			return leftIndex;
		} else if (nums[leftIndex] == nums[rightIndex]) {
			return Math.min(leftIndex, rightIndex);
		} else {
			return rightIndex;
		}
	}
}