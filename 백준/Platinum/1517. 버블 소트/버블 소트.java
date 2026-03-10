import java.io.*;
import java.util.*;

public class Main {
	private static long[] indexTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		long[] nums = new long[N];
		long[] copy = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(st.nextToken());
			copy[i] = nums[i];
		}

		Arrays.sort(copy);
		indexTree = new long[N * 4];

		int index = 0;
		Map<Long, Integer> map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			if (map.containsKey(copy[i])) {
				continue;
			}
			map.put(copy[i], index++);
		}

		long answer = 0L;
		for (int i = 0; i < N; i++) {
			index = map.get(nums[i]);

			answer += queryCount(1, 0, N - 1, index + 1, N - 1);
			queryUpdate(1, 0, N - 1, index, 1);
		}
		
		System.out.println(answer);
	}

	private static long queryCount(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || queryLeft > nodeRight) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return indexTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long left = queryCount(next, nodeLeft, mid, queryLeft, queryRight);
		long right = queryCount(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return left + right;
	}

	private static void queryUpdate(int node, int nodeLeft, int nodeRight, int index, int count) {
		if (nodeLeft > index || nodeRight < index) {
			return;
		}

		indexTree[node] += count;

		if (nodeLeft == nodeRight)
			return;

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		queryUpdate(next, nodeLeft, mid, index, count);
		queryUpdate(next + 1, mid + 1, nodeRight, index, count);
	}
}