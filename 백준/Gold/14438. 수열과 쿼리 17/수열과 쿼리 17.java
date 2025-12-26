import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] nums;
	private static long[] minTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		minTree = new long[N * 4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, 0, N - 1);
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if (a == 1) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				long c = Long.parseLong(st.nextToken());
				update(1, 0, N - 1, b, c);
			} else {
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				sb.append(minQuery(1, 0, N - 1, b, c)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			minTree[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		init(node << 1, nodeLeft, mid);
		init((node << 1) + 1, mid + 1, nodeRight);
		minTree[node] = Math.min(minTree[node << 1], minTree[(node << 1) + 1]);
	}

	private static void update(int node, int nodeLeft, int nodeRight, int queryIndex, long value) {
		if (nodeRight < queryIndex || queryIndex < nodeLeft) {
			return;
		}

		if (nodeLeft == nodeRight) {
			minTree[node] = value;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		update(node << 1, nodeLeft, mid, queryIndex, value);
		update((node << 1) + 1, mid + 1, nodeRight, queryIndex, value);
		minTree[node] = Math.min(minTree[node << 1], minTree[(node << 1) + 1]);
	}

	private static long minQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (nodeRight < queryLeft || nodeLeft > queryRight) {
			return Long.MAX_VALUE;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return minTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		long minLeft = minQuery(node << 1, nodeLeft, mid, queryLeft, queryRight);
		long minRight = minQuery((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return Math.min(minLeft, minRight);
	}
}