import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static int[] nums;
	private static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String line;

		while ((line = br.readLine()) != null) {

			st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			nums = new int[N + 1];
			tree = new int[N * 4];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				int num = Integer.parseInt(st.nextToken());
				int state = 0;
				if (num > 0) {
					state = 1;
				} else if (num < 0) {
					state = -1;
				}
				nums[i] = state;
			}

			init(1, 1, N);

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				char order = st.nextToken().charAt(0);

				if (order == 'C') {
					int queryIndex = Integer.parseInt(st.nextToken());
					int num = Integer.parseInt(st.nextToken());

					int state = 0;
					if (num > 0) {
						state = 1;
					} else if (num < 0) {
						state = -1;
					}

					updateQuery(1, 1, N, queryIndex, state);
				} else {
					int queryLeft = Integer.parseInt(st.nextToken());
					int queryRight = Integer.parseInt(st.nextToken());
					int value = stateQuery(1, 1, N, queryLeft, queryRight);

					char result = '0';
					if (value > 0) {
						result = '+';
					} else if (value < 0) {
						result = '-';
					}
					sb.append(result);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int stateQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 1;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return tree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftState = stateQuery(next, nodeLeft, mid, queryLeft, queryRight);
		int rightState = stateQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftState * rightState;
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int queryIndex, int value) {
		if (queryIndex < nodeLeft || nodeRight < queryIndex) {
			return;
		}

		if (nodeLeft == nodeRight) {
			tree[node] = value;
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, queryIndex, value);
		updateQuery(next + 1, mid + 1, nodeRight, queryIndex, value);
		tree[node] = tree[next] * tree[next + 1];
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			tree[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		init(next, nodeLeft, mid);
		init(next + 1, mid + 1, nodeRight);
		tree[node] = tree[next] * tree[next + 1];
	}
}