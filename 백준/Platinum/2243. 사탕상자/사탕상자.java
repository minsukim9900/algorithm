import java.io.*;
import java.util.*;

public class Main {
	private static final int MAX = 1_000_000;
	private static int[] indexTree = new int[MAX * 4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());

			if (order == 1) {
				int count = Integer.parseInt(st.nextToken());
				int number = searchQuery(1, 1, MAX, count);
				updateQuery(1, 1, MAX, number, -1);
				sb.append(number).append("\n");
			} else {
				int target = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				updateQuery(1, 1, MAX, target, diff);
			}
		}
		System.out.println(sb.toString());
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target, int diff) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		indexTree[node] += diff;

		if (nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target, diff);
		updateQuery(next + 1, mid + 1, nodeRight, target, diff);
	}

	private static int searchQuery(int node, int nodeLeft, int nodeRight, int count) {
		if (nodeLeft == nodeRight) {
			return nodeLeft;
		}

		int next = node << 1;
		int leftCount = indexTree[next];
		int mid = (nodeLeft + nodeRight) >> 1;
		
		if (count <= leftCount) {
			return searchQuery(next, nodeLeft, mid, count);
		} else {
			return searchQuery(next + 1, mid + 1, nodeRight, count - leftCount);
		}
	}
}