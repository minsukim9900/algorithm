import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] trees;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		trees = new int[N * 4];
		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int order = Integer.parseInt(st.nextToken());

			if (order == 1) {
				int queryIndex = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				updateQuery(1, 0, N - 1, queryIndex - 1, value);
			} else {
				int target = Integer.parseInt(st.nextToken());
				sb.append(countQuery(1, 0, N - 1, target)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			trees[node] = nums[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		init(next, nodeLeft, mid);
		init(next + 1, mid + 1, nodeRight);
		trees[node] = trees[next] + trees[next + 1];
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int queryIndex, int value) {
		if (queryIndex < nodeLeft || nodeRight < queryIndex) {
			return;
		}

		trees[node] += value;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, queryIndex, value);
		updateQuery(next + 1, mid + 1, nodeRight, queryIndex, value);
		trees[node] = trees[next] + trees[next + 1];
	}

	private static int countQuery(int node, int nodeLeft, int nodeRight, int target) {
		if (nodeLeft == nodeRight) {
			return nodeLeft + 1;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftCount = trees[next];

		if (leftCount >= target) {
			return countQuery(next, nodeLeft, mid, target);
		} else {
			return countQuery(next + 1, mid + 1, nodeRight, target - leftCount);
		}
	}
}