import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static final int MAX = 200_000;
	private static int[] tree;
	private static int[] pos;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			pos = new int[N + 1];

			int front = 100_000;

			tree = new int[MAX * 4];

			for (int i = 1; i < N + 1; i++) {
				pos[i] = 100_000 + i;
				updateQuery(1, 1, MAX, pos[i], 1);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int idx = Integer.parseInt(st.nextToken());
				int curPos = pos[idx];

				int result = searchQuery(1, 1, MAX, 1, curPos - 1);
				sb.append(result).append(" ");
				updateQuery(1, 1, MAX, curPos, -1);
				updateQuery(1, 1, MAX, front, 1);

				pos[idx] = front;
				front--;
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int searchQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return tree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftCount = searchQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightCount = searchQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftCount + rightCount;
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
}