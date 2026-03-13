import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long[] tree;
	private static final int MAX = 1_000_000;
	private static int[] pos = new int[MAX + 1];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		tree = new long[N * 4];

		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int[] B = new int[N];

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			pos[num] = i + 1;
		}

		long answer = 0;
		for (int i = 0; i < N; i++) {
			answer += searchQuery(1, 1, N, pos[A[i]] + 1, N);
			updateQuery(1, 1, N, pos[A[i]]);
		}

		System.out.println(answer);
	}

	private static void updateQuery(int node, int nodeLeft, int nodeRight, int target) {
		if (target < nodeLeft || nodeRight < target) {
			return;
		}

		tree[node]++;
		
		if(nodeLeft == nodeRight) {
			return;
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		updateQuery(next, nodeLeft, mid, target);
		updateQuery(next + 1, mid + 1, nodeRight, target);
	}

	private static long searchQuery(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}

		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return tree[node];
		}

		int mid = (nodeLeft + nodeRight) >> 1;
		int next = node << 1;

		long leftCount = searchQuery(next, nodeLeft, mid, queryLeft, queryRight);
		long rightCount = searchQuery(next + 1, mid + 1, nodeRight, queryLeft, queryRight);

		return leftCount + rightCount;

	}
}