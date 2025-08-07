import java.io.*;
import java.security.CryptoPrimitive;
import java.util.*;

public class Solution {
	private static int N;
	private static int[] nums;
	private static int[] cnt;
	private static int max, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			cnt = new int[4];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cnt[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, new int[N - 1]);
			sb.append("#").append(t).append(" ").append(max - min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int depth, int[] operator) {
		if (depth == N - 1) {
			int result = cal(operator);
			min = Math.min(min, result);
			max = Math.max(max, result);
		} else {
			for (int i = 0; i < 4; i++) {
				if (cnt[i] == 0)
					continue;
				operator[depth] = i;
				cnt[i]--;
				dfs(depth + 1, operator);
				cnt[i]++;
			}
		}
	}

	private static int cal(int[] operator) {
		int sum = nums[0];

		for (int i = 1; i < N; i++) {
			int curr = nums[i];
			switch (operator[i - 1]) {
			case 0:
				sum += curr;
				break;
			case 1:
				sum -= curr;
				break;
			case 2:
				sum *= curr;
				break;
			case 3:
				sum /= curr;
				break;
			}
		}
		return sum;
	}
}