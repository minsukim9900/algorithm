import java.io.*;
import java.util.*;

public class Main {
	private static int N, Q;
	private static long[] sumTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		sumTree = new long[N * 4];

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int order = Integer.parseInt(st.nextToken());

			if (order == 1) {
				int index = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				queryUpdate(1, 1, N, index, value);
			} else {
				int queryLeft = Integer.parseInt(st.nextToken());
				int queryRight = Integer.parseInt(st.nextToken());
				sb.append(querySum(1, 1, N, queryLeft, queryRight)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void queryUpdate(int node, int nodeLeft, int nodeRight, int queryIndex, int value) {
		if (queryIndex < nodeLeft || nodeRight < queryIndex) {
			return;
		}

		if (nodeLeft == nodeRight) {
			sumTree[node] += value;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int leftChild = node << 1;
		int rightChild = leftChild + 1;

		queryUpdate(leftChild, nodeLeft, mid, queryIndex, value);
		queryUpdate(rightChild, mid + 1, nodeRight, queryIndex, value);
		sumTree[node] = sumTree[leftChild] + sumTree[rightChild];
	}

	private static long querySum(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || queryLeft > nodeRight) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return sumTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int leftChild = (node << 1);
		int rightChild = leftChild + 1;

		long leftSum = querySum(leftChild, nodeLeft, mid, queryLeft, queryRight);
		long rightSum = querySum(rightChild, mid + 1, nodeRight, queryLeft, queryRight);
		return leftSum + rightSum;
	}
}