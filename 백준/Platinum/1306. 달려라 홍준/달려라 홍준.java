import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] maxTree, nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maxTree = new int[N * 4];
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		init(1, 0, N - 1);

		for (int i = M - 1; i <= N - M; i++) {
			int queryLeft = i - (M - 1);
			int queryRight = i + (M - 1);

			int result = maxQuery(1, 0, N - 1, queryLeft, queryRight);
			sb.append(result).append(" ");
		}

		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			maxTree[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		init(next, nodeLeft, mid);
		init(next + 1, mid + 1, nodeRight);

		maxTree[node] = Math.max(maxTree[next], maxTree[next + 1]);

	}

	private static int maxQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return maxTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftValue = maxQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightValue = maxQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return Math.max(leftValue, rightValue);

	}
}