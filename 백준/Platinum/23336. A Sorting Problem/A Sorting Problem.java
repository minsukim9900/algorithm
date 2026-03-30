import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long[] countTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		long answer = 0;
		countTree = new long[N * 4];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int idx = Integer.parseInt(st.nextToken()) - 1;
			answer += getCountQuery(1, 0, N - 1, idx + 1, N - 1);
			updateQuery(1, 0, N - 1, idx, 1);
		}

		System.out.println(answer);
	}

	private static long getCountQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return countTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long leftCount = getCountQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightCount = getCountQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftCount + rightCount;
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target, int diff) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		countTree[node] += diff;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target, diff);
		updateQuery(next + 1, mid + 1, nodeRight, target, diff);
	}
}