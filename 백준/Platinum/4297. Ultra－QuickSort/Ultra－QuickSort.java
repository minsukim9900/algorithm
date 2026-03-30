import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long[] countTree;
	private static TreeMap<Integer, Integer> treeMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			N = Integer.parseInt(br.readLine());

			if (N == 0) {
				break;
			}

			treeMap = new TreeMap<>();
			countTree = new long[N * 4];
			int[] order = new int[N];

			int idx = 0;
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(br.readLine());
				treeMap.put(num, idx++);
			}

			idx = 0;
			for (Integer key : treeMap.keySet()) {
				int index = treeMap.get(key);
				order[index] = idx++;
			}

			long answer = 0;
			for (int i = 0; i < N; i++) {
				answer += getCountQuery(1, 0, N - 1, order[i] + 1, N - 1);
				updateQuery(1, 0, N - 1, order[i], 1);
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
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