import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] nums;
	private static int[] result;
	private static boolean[] visited;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		result = new int[M + 1];
		visited = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		dfs(1);
		System.out.println(sb.toString());

	}

	public static void dfs(int depth) {

		if (depth == M + 1) {
			for (int i = 1; i <= M; i++) {
				System.out.print(result[i] + " ");
				// sb.append(result[i]).append(" ");
			}
			// sb.append("\n");
			System.out.println();

		} else {
			int tmp = 0;

			for (int i = 1; i <= N; i++) {

				if (visited[i]) {
					continue;
				}

				if (tmp != nums[i]) {
					tmp = nums[i];
					visited[i] = true;
					result[depth] = nums[i];
					dfs(depth + 1);
					visited[i] = false;
				}

			}
		}
	}

}
