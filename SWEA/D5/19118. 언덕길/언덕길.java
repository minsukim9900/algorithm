import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static int[] heights;

	private static int binarySearch(int s, int e, int target, int[] dp) {
		int result = 0;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (dp[mid] >= target) {
				result = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());

			heights = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}

			int idx = 1;
			int[] dp = new int[N];

			dp[0] = heights[0];

			for (int i = 1; i < N; i++) {
				int height = heights[i];

				if (dp[idx - 1] < height) {
					dp[idx++] = height;
				} else {
					int sIdx = binarySearch(0, idx - 1, height, dp);

					dp[sIdx] = height;
				}
			}

			sb.append("#").append(t).append(" ").append(N - idx).append("\n");
		}

		System.out.println(sb.toString());
	}
}
