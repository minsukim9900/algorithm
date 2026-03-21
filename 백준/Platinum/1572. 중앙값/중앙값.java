import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static final int MAX = 65535;
	private static int[] nums;
	private static int[] numberCountTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		numberCountTree = new int[MAX * 4];
		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int midIndex = (K + 1) >> 1;

		for (int i = 0; i < K - 1; i++) {
			updateQuery(1, 0, MAX, nums[i], 1);
		}

		long answer = 0L;
		for (int i = K - 1; i < N; i++) {
			updateQuery(1, 0, MAX, nums[i], 1);
			int number = findNumberQuery(1, 0, MAX, midIndex);
			answer += number;
			updateQuery(1, 0, MAX, nums[i + 1 - K], -1);
		}

		System.out.println(answer);
	}

	private static int findNumberQuery(int node, int nodeLeft, int nodeRight, int count) {
		if (nodeLeft == nodeRight) {
			return nodeLeft;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		int leftCount = numberCountTree[next];

		if (leftCount >= count) {
			return findNumberQuery(next, nodeLeft, mid, count);
		} else {
			return findNumberQuery(next + 1, mid + 1, nodeRight, count - leftCount);
		}
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target, int value) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		numberCountTree[node] += value;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target, value);
		updateQuery(next + 1, mid + 1, nodeRight, target, value);

		numberCountTree[node] = numberCountTree[next] + numberCountTree[next + 1];
	}
}