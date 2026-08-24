import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, max;
	private static int[] nums, diff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			max = 0;

			nums = new int[N];

			int answer = 0;

			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());

				max = Math.max(max, num);

				nums[i] = num;
			}

			int sum = 0;
			int oddCount = 0;

			for (int i = 0; i < N; i++) {
				int num = nums[i];
				int diff = max - num;

				sum += diff;
				oddCount += diff % 2;
			}

			int devide = sum / 3;
			int mod = sum % 3;

			answer = (devide * 2) + mod;
			answer = Math.max(answer, oddCount * 2 - 1);

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}
}
