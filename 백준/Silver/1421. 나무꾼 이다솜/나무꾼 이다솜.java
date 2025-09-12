import java.io.*;
import java.util.*;;

public class Main {
	private static int N, C, W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		int[] nums = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, nums[i]);
		}

		long answer = 0L;
		for (int i = 1; i <= max; i++) {
			long total = 0L;

			for (int j = 0; j < N; j++) {
				int q = nums[j] / i;

				int cuts = (nums[j] % i == 0) ? (q - 1) : q;
				long revenue = q * i * W;
				long cost = cuts * C;
				long profit = revenue - cost;

				if (profit > 0)
					total += profit;
			}
			
			answer = Math.max(answer, total);
		}
		System.out.println(answer);
	}
}
