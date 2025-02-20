import java.io.*;
import java.util.*;

public class Solution {

	private static int N, result;
	private static char[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			result = 0;
			String str = st.nextToken();

			N = Integer.parseInt(st.nextToken());
			nums = new char[str.length()];

			for (int i = 0; i < nums.length; i++) {
				nums[i] = str.charAt(i);
			}
			

			dfs(0, 0);
			sb.append("#").append(t).append(" ").append(result).append("\n");

		}
		System.out.println(sb.toString());

	}

	private static void dfs(int st, int depth) {

		if (depth == N) {
			int tmp = Integer.parseInt(new String(nums));
			result = Math.max(result, tmp);
		} else {

			for (int i = st; i < nums.length - 1; i++) {

				for (int j = i + 1; j < nums.length; j++) {
					swap(i, j);
					dfs(i, depth + 1);
					swap(i, j);
				}

			}
		}
	}

	private static void swap(int x, int y) {
		char tmp = nums[x];
		nums[x] = nums[y];
		nums[y] = tmp;
	}

}
