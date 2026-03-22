import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] indexTree;
	private static int[] heights;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		indexTree = new int[N * 4];
		heights = new int[N];

		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}

		init(1, 0, N - 1);
		System.out.println(cal(0, N - 1));
	}

	private static long cal(int queryLeft, int queryRight) {
		int width = queryRight - queryLeft + 1;
		int index = getIndexQuery(1, 0, N - 1, queryLeft, queryRight);
		long height = heights[index];
		long result = width * height;

		long leftResult = 0L;
		long rightResult = 0L;

		if (queryLeft <= index - 1) {
			leftResult = cal(queryLeft, index - 1);
		}

		if (index + 1 <= queryRight) {
			rightResult = cal(index + 1, queryRight);
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
		
		int leftValue = heights[indexTree[next]];
		int rightValue = heights[indexTree[next + 1]];

		indexTree[node] = leftValue <= rightValue ? indexTree[next] : indexTree[next + 1];
	}

	private static int getIndexQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return -1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return indexTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftIndex = getIndexQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightIndex = getIndexQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		if (leftIndex == -1) {
			return rightIndex;
		}

		if (rightIndex == -1) {
			return leftIndex;
		}

		int leftValue = heights[leftIndex];
		int rightValue = heights[rightIndex];

		return leftValue <= rightValue ? leftIndex : rightIndex;
	}
}