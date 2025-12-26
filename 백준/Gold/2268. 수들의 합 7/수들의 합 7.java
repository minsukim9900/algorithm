import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static long[] sumTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sumTree = new long[N * 4];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if (a == 0) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				
				int s = Math.min(b, c);
				int e = Math.max(b, c);
				
				sb.append(sumQuery(1, 0, N - 1, s, e)).append("\n");
			} else {
				int b = Integer.parseInt(st.nextToken()) - 1;
				long c = Integer.parseInt(st.nextToken());
				update(1, 0, N - 1, b, c);
			}
		}
		System.out.println(sb.toString());
	}

	private static void update(int node, int nodeLeft, int nodeRight, int queryIndex, long value) {
		if (nodeRight < queryIndex || queryIndex < nodeLeft) {
			return;
		}

		if (nodeLeft == nodeRight) {
			sumTree[node] = value;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		update(node << 1, nodeLeft, mid, queryIndex, value);
		update((node << 1) + 1, mid + 1, nodeRight, queryIndex, value);
		sumTree[node] = sumTree[node << 1] + sumTree[(node << 1) + 1];
	}

	private static long sumQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (nodeRight < queryLeft || nodeLeft > queryRight) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return sumTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		long sumLeft = sumQuery(node << 1, nodeLeft, mid, queryLeft, queryRight);
		long sumRight = sumQuery((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return sumLeft + sumRight;
	}
}