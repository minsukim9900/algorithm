import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, answer;
	private static char[] nums;
	private static Set<String>[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());

			nums = st.nextToken().toCharArray();
			N = nums.length;
			M = Integer.parseInt(st.nextToken());
			answer = 0;

			visited = new HashSet[M + 1];
			for (int i = 0; i < M + 1; i++) {
				visited[i] = new HashSet<>();
			}

			dfs(0);

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void swap(int a, int b) {
		char temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	private static void dfs(int depth) {
		String current = String.valueOf(nums);

		if (visited[depth].contains(current)) {
			return;
		}

		visited[depth].add(current);

		if (depth == M) {
			int result = Integer.parseInt(String.valueOf(nums));
			answer = Math.max(answer, result);
			return;
		}

		for (int a = 0; a < N - 1; a++) {
			for (int b = a + 1; b < N; b++) {
				swap(a, b);
				dfs(depth + 1);
				swap(a, b);
			}
		}
	}
}
