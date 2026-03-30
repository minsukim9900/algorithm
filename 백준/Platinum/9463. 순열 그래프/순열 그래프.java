import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] nums, order;
	private static long[] numberTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			numberTree = new long[N * 4];

			nums = new int[N + 1];
			order = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				order[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int index = Integer.parseInt(st.nextToken());
				nums[index] = i + 1;
			}

			long answer = 0L;
			for (int i = 0; i < N; i++) {
				int num = nums[order[i]];
				answer += countQuery(1, 1, N, num + 1, N);
				updateQuery(1, 1, N, num, 1);
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static long countQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return numberTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long leftCount = countQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightCount = countQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftCount + rightCount;
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target, int diff) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		numberTree[node] += diff;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target, diff);
		updateQuery(next + 1, mid + 1, nodeRight, target, diff);
	}
}