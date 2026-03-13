import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		tree = new int[N * 4];

		int index = 1;
		Map<Integer, Integer> map = new HashMap<>();

		int[] nums = new int[N];
		int[] copy = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			copy[i] = nums[i];
		}


		Arrays.sort(copy);
		for (int i = 0; i < N; i++) {
			if (!map.containsKey(copy[i])) {
				map.put(copy[i], index++);
			}
		}

		for (int i = 0; i < N; i++) {
			int idx = map.get(nums[i]);
			int result = searchQuery(1, 1, N, idx + 1, N) + 1;
			sb.append(result).append("\n");
			updateQuery(1, 1, N, idx);
		}

		System.out.println(sb.toString());
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		tree[node]++;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target);
		updateQuery(next + 1, mid + 1, nodeRight, target);
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
}