import java.io.*;
import java.util.*;

public class Main {
	private static final int MAX = 2_000_000;
	private static int[] indexTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		indexTree = new int[MAX * 4];

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int order = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());

			if (order == 1) {
				updateQury(1, 1, MAX, number, 1);
			} else {
				int result = searchQuery(1, 1, MAX, number);
				updateQury(1, 1, MAX, result, -1);
				sb.append(result).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void updateQury(int node, int nodeLeft, int nodeRight, int target, int diff) {
		if (nodeRight < target || target < nodeLeft) {
			return;
		}

		indexTree[node] += diff;
		
		if(nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQury(next, nodeLeft, mid, target, diff);
		updateQury(next + 1, mid + 1, nodeRight, target, diff);
	}

	private static int searchQuery(int node, int nodeLeft, int nodeRight, int count) {
		if (nodeLeft == nodeRight) {
			return nodeLeft;
		}

		int next = node << 1;

		int leftCount = indexTree[next];
		int mid = (nodeLeft + nodeRight) >> 1;

		if (leftCount >= count) {
			return searchQuery(next, nodeLeft, mid, count);
		} else {
			return searchQuery(next + 1, mid + 1, nodeRight, count - leftCount);
		}
	}
}