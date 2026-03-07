import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] indexTree;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		indexTree = new int[N * 4];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		init(1, 0, N - 1);
		System.out.println(getMaxExtent(0, N - 1));
	}

	private static long getMaxExtent(int queryLeft, int queryRight) {
		int width = queryRight - queryLeft + 1;
		int index = querySearchMinHeightIndex(1, 0, N - 1, queryLeft, queryRight);
		int height = nums[index];

		long result = width * height;
		long left = 0;
		long right = 0;

		if (index - 1 >= queryLeft) {
			left = getMaxExtent(queryLeft, index - 1);
		}

		if (index + 1 <= queryRight) {
			right = getMaxExtent(index + 1, queryRight);
		}

		return Math.max(result, Math.max(left, right));
	}

	private static int init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			return indexTree[node] = nodeLeft;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int left = init(next, nodeLeft, mid);
		int right = init(next + 1, mid + 1, nodeRight);

		return indexTree[node] = nums[left] <= nums[right] ? left : right;
	}

	private static int querySearchMinHeightIndex(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return -1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return indexTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int left = querySearchMinHeightIndex(next, nodeLeft, mid, queryLeft, queryRight);
		int right = querySearchMinHeightIndex(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		if (left == -1) {
			return right;
		} else if (right == -1) {
			return left;
		}

		return nums[left] <= nums[right] ? left : right;
	}
}