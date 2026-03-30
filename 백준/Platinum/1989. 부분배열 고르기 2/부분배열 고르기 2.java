import java.io.*;
import java.util.*;

public class Main {
	private static int N, left, right;
	private static int[] nums;
	private static long[] prefix;
	private static int[] indexTree;
	private static long answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		nums = new int[N + 1];
		prefix = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			prefix[i] = prefix[i - 1] + nums[i];
		}

		indexTree = new int[N * 4];
		answer = -1;
		
		init(1, 1, N);

		simulate(1, N);
		sb.append(answer).append("\n");
		sb.append(left).append(" ").append(right);

		System.out.println(sb.toString());
	}

	private static long simulate(int queryLeft, int queryRight) {
		long width = prefix[queryRight] - prefix[queryLeft - 1];
		int heightIndex = getQuery(1, 1, N, queryLeft, queryRight);

		int height = nums[heightIndex];

		long result = (long) width * height;

		if (answer < result) {
			answer = result;
			left = queryLeft;
			right = queryRight;
		}

		long leftResult = 0;
		long rightResult = 0;

		if (queryLeft <= heightIndex - 1) {
			leftResult = simulate(queryLeft, heightIndex - 1);
		}

		if (heightIndex + 1 <= queryRight) {
			rightResult = simulate(heightIndex + 1, queryRight);
		}

		return Math.max(result, Math.max(leftResult, rightResult));
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			indexTree[node] = nodeLeft;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		init(next, nodeLeft, mid);
		init(next + 1, mid + 1, nodeRight);

		int leftValue = nums[indexTree[next]];
		int rightValue = nums[indexTree[next + 1]];

		indexTree[node] = leftValue <= rightValue ? indexTree[next] : indexTree[next + 1];
	}

	private static int getQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return -1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return indexTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftIndex = getQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightIndex = getQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		if (leftIndex == -1) {
			return rightIndex;
		}

		if (rightIndex == -1) {
			return leftIndex;
		}

		int leftValue = nums[leftIndex];
		int rightValue = nums[rightIndex];

		return leftValue <= rightValue ? leftIndex : rightIndex;
	}
}