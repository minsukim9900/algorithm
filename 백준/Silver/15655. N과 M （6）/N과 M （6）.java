import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] result;
	private static int[] nums;
	private static boolean[] visited;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[M + 1];
		nums = new int[N + 1];
		visited = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		dfs(1, 1);
		System.out.println(sb.toString());

	}

	public static void dfs(int num, int depth) {

		if (depth == M + 1) {

			for (int i = 1; i <= M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");

		} else {

			for (int i = num; i <= N; i++) {
				if (visited[i])
					continue;
				visited[i] = true;
				result[depth] = nums[i];
				dfs(i + 1, depth + 1);
				visited[i] = false;
			}

		}

	}

}
