import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, X, Y;
	private static int[] nums;
	private static boolean[] error;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		error = new boolean[N + 1];
		nums = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			error[nums[i]] = true;
		}
		Arrays.sort(nums);

		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		if (X == 0) {
			System.out.println(M - Y);
			return;
		}

		int maxDiff = Math.max(nums[0] - 1, N - nums[M - 1]);
		for (int i = 1; i < M; i++) {
			maxDiff = Math.max(maxDiff, nums[i] - nums[i - 1]);
		}

		if (maxDiff >= X) {
			System.out.println(M - Y);
			return;
		}
		System.out.println(slidingWindow());
	}

	private static int slidingWindow() {
		int cnt = 0;
		for (int i = 1; i <= X; i++) {
			if (error[i])
				cnt++;
		}

		int min = cnt;
		for (int i = 2; i <= N - X + 1; i++) {
			if (error[i - 1])
				cnt--;
			if (error[i + X - 1])
				cnt++;
			if (cnt < min) {
				min = cnt;
				if (min < Y)
					break;
			}
		}

		return min < Y ? (M - Y) : (M - min);
	}
}