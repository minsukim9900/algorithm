import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long[][] nums;
	private static long[][] sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new long[4][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nums[0][i] = Integer.parseInt(st.nextToken());
			nums[1][i] = Integer.parseInt(st.nextToken());
			nums[2][i] = Integer.parseInt(st.nextToken());
			nums[3][i] = Integer.parseInt(st.nextToken());
		}
		
		sum = new long[2][N * N];
		
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum[0][idx] = nums[0][i] + nums[1][j];
				sum[1][idx++] = nums[2][i] + nums[3][j];
			}
		}

		for (int i = 0; i < 2; i++) {
			Arrays.sort(sum[i]);
		}

		long answer = 0;
		for (long rs : sum[0]) {
			answer += uppder_bound(-1 * rs) - lower_bound(-1 * rs);
		}
		System.out.println(answer);
	}

	private static int lower_bound(long target) {
		int s = 0;
		int e = N * N - 1;
		
		while (s <= e) {
			int mid = (s + e) / 2;
			
			if (sum[1][mid] < target) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return s;
	}

	private static int uppder_bound(long target) {
		int s = 0;
		int e = N * N - 1;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (sum[1][mid] <= target) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return s;
	}
}