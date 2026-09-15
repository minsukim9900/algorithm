import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, answer;
	private static char[] nums;

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

			if (M > 6) {
				M = (M % 2 == 0) ? 6 : 5;
			}
			answer = 0;

			dfs(0, 0);

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void swap(int a, int b) {
		char temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	private static void dfs(int sIdx, int depth) {
		if (depth == M) {
			int result = Integer.parseInt(String.valueOf(nums));
			answer = Math.max(answer, result);
			return;
		}

		for (int a = sIdx; a < N - 1; a++) {
			for (int b = a + 1; b < N; b++) {
				swap(a, b);
				dfs(a, depth + 1);
				swap(a, b);
			}
		}
	}
}
