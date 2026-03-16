import java.io.*;
import java.util.*;

public class Main {
	private static final int MAX = 1_000_001;
	private static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		tree = new int[MAX * 4];

		st = new StringTokenizer(br.readLine());
		long answer = 0L;
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			answer += countQuery(1, 1, N, number + 1, N);
			updateQuery(1, 1, N, number, 1);
		}

		System.out.println(answer);
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target, int diff) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		tree[node] += diff;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target, diff);
		updateQuery(next + 1, mid + 1, nodeRight, target, diff);
	}

	private static int countQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return tree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftCount = countQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightCount = countQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return leftCount + rightCount;
	}
}