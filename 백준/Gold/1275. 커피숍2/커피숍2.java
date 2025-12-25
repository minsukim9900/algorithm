import java.io.*;
import java.util.*;

public class Main {
	private static int N, Q;
	private static long[] nums;
	private static long[] sumTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		nums = new long[N];
		sumTree = new long[N * 4];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(st.nextToken());
		}

		init(1, 0, N - 1);

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			int queryLeft = Math.min(x, y);
			int queryRight = Math.max(x, y);
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			sb.append(querySum(1, 0, N - 1, queryLeft, queryRight)).append("\n");
			queryUpdate(1, 0, N - 1, a, b);
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			sumTree[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		init(node << 1, nodeLeft, mid);
		init((node << 1) + 1, mid + 1, nodeRight);

		sumTree[node] = sumTree[node << 1] + sumTree[(node << 1) + 1];
	}

	private static void queryUpdate(int node, int nodeLeft, int nodeRight, int queryIndex, int value) {

		if (queryIndex < nodeLeft || queryIndex > nodeRight) {
			return;
		}

		if (nodeLeft == nodeRight) {
			sumTree[node] = value;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		queryUpdate(node << 1, nodeLeft, mid, queryIndex, value);
		queryUpdate((node << 1) + 1, mid + 1, nodeRight, queryIndex, value);
		sumTree[node] = sumTree[node << 1] + sumTree[(node << 1) + 1];
	}

	private static long querySum(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {

		if (queryRight < nodeLeft || queryLeft > nodeRight) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return sumTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		long leftSum = querySum(node << 1, nodeLeft, mid, queryLeft, queryRight);
		long rightSum = querySum((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return leftSum + rightSum;
	}
}