import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] inOrder, postOrder, inOrderIndex;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		inOrder = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}

		postOrder = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}

		inOrderIndex = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			inOrderIndex[inOrder[i]] = i;
		}
		dfs(1, N, 1, N);
		System.out.println(sb.toString());
	}

	private static StringBuilder sb = new StringBuilder();

	private static void dfs(int is, int ie, int ps, int pe) {
		if (is <= ie && ps <= pe) {
			int root = postOrder[pe];
			sb.append(root).append(" ");

			dfs(is, inOrderIndex[root] - 1, ps, ps + inOrderIndex[root] - is - 1);
			dfs(inOrderIndex[root] + 1, ie, ps + inOrderIndex[root] - is, pe - 1);
		}
	}
}
