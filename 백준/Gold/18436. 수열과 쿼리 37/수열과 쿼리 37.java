import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] nums;
	private static Node[] countTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		countTree = new Node[N * 4];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < countTree.length; i++) {
			countTree[i] = new Node(0, 0);
		}

		init(1, 0, N - 1);

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if (a == 1) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());

				update(1, 0, N - 1, b, c);
			} else if (a == 2) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				Node node = countQuery(1, 0, N - 1, b, c);
				sb.append(node.even).append("\n");
			} else {
				int b = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				Node node = countQuery(1, 0, N - 1, b, c);
				sb.append(node.odd).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void init(int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			if (nums[nodeLeft] % 2 == 1) {
				countTree[node].odd++;
			} else {
				countTree[node].even++;
			}
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		init(node << 1, nodeLeft, mid);
		init((node << 1) + 1, mid + 1, nodeRight);

		Node leftCountTree = countTree[node << 1];
		Node rightCountTree = countTree[(node << 1) + 1];

		countTree[node].even = leftCountTree.even + rightCountTree.even;
		countTree[node].odd = leftCountTree.odd + rightCountTree.odd;
	}

	private static void update(int node, int nodeLeft, int nodeRight, int queryIndex, int value) {
		if (queryIndex < nodeLeft || nodeRight < queryIndex) {
			return;
		}

		if (nodeLeft == nodeRight) {
			if (value % 2 == 1) {
				if (countTree[node].odd == 1) {
					return;
				} else if (countTree[node].even == 1) {
					countTree[node].even--;
					countTree[node].odd++;
				}
			} else {
				if (countTree[node].even == 1) {
					return;
				} else if (countTree[node].odd == 1) {
					countTree[node].odd--;
					countTree[node].even++;
				}
			}
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		update(node << 1, nodeLeft, mid, queryIndex, value);
		update((node << 1) + 1, mid + 1, nodeRight, queryIndex, value);

		Node leftCountTree = countTree[node << 1];
		Node rightCountTree = countTree[(node << 1) + 1];

		countTree[node].even = leftCountTree.even + rightCountTree.even;
		countTree[node].odd = leftCountTree.odd + rightCountTree.odd;
	}

	private static Node countQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return new Node(0, 0);
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return countTree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;

		Node left = countQuery(node << 1, nodeLeft, mid, queryLeft, queryRight);
		Node right = countQuery((node << 1) + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return new Node(left.odd + right.odd, left.even + right.even);
	}

	private static class Node {
		int odd, even;

		public Node(int odd, int even) {
			this.odd = odd;
			this.even = even;
		}
	}
}