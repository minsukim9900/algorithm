import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] indexTree;
	private static long[] height;
	private static final int INF = Integer.MAX_VALUE;
	private static long answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			if (N == 0) {
				break;
			}

			indexTree = new int[N * 4];
			height = new long[N];
			answer = 0;

			for (int i = 0; i < N; i++) {
				height[i] = Long.parseLong(st.nextToken());
			}

			init(1, 0, N - 1);
			System.out.println(cal(0, N - 1));
		}

	}

	private static long cal(int leftNode, int rightNode) {
		int width = rightNode - leftNode + 1;
		int index = queryHeight(1, 0, N - 1, leftNode, rightNode);
		long heightValue = height[index];

		long result = width * heightValue;
		long left = 0;
		long right = 0;
		if(leftNode <= index - 1) {
			left = cal(leftNode, index - 1);
		}
		
		if(index + 1 <= rightNode) {
			right = cal(index + 1, rightNode);
		}

		return Math.max(result, Math.max(left, right));
	}

	private static int init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			indexTree[node] = nodeLeft;
			return nodeLeft;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		int nextNode = node << 1;

		int left = init(nextNode, nodeLeft, mid);
		int right = init(nextNode + 1, mid + 1, nodeRight);

		indexTree[node] = height[left] <= height[right] ? left : right;
		return indexTree[node];
	}

	private static int queryHeight(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryLeft > nodeRight || queryRight < nodeLeft) {
			return -1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return indexTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int left = queryHeight(next, nodeLeft, mid, queryLeft, queryRight);
		int right = queryHeight(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		if (left == -1) {
			return right;
		} else if (right == -1) {
			return left;
		}

		return height[left] <= height[right] ? left : right;
	}
}