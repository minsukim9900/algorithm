import java.io.*;
import java.util.*;;

public class Main {
	private static int N, K, P, X;
	private static int[] nums = { 123, 10, 55, 31, 78, 93, 125, 11, 127, 95 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[][] dff = new int[10][10];

		for (int i = 0; i < nums.length - 1; i++) {
			int x = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				int y = nums[j];
				int cnt = Integer.bitCount(x ^ y);

				dff[i][j] = cnt;
				dff[j][i] = cnt;
			}
		}

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;

			int comA = X;
			int comB = i;
			int cnt = 0;
			for (int j = 0; j < K; j++) {
				int x = comA % 10;
				int y = comB % 10;

				cnt += dff[x][y];
				comA /= 10;
				comB /= 10;
			}
			if (cnt <= P) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}
