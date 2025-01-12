import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] result, nums;
	private static StringBuilder sb = new StringBuilder();
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		result = new int[M];
		nums = new int[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		dfs(0);
		System.out.println(sb.toString());

	}

	private static void dfs(int depth) {

		if (depth == M) {

			for (int i = 0; i < M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");

		} else {

			int pre = -1;
			for (int i = 0; i < N; i++) {

				if (nums[i] != pre && !visited[i]) {
					pre = nums[i];
					visited[i] = true;
					result[depth] = nums[i];
					dfs(depth + 1);
					visited[i] = false;

				}

			}

		}

	}

}
