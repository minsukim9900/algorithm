import java.io.*;
import java.util.*;

public class Main {

	private static int C, N;
	private static int[][] info;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		info = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(info, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int[] dp = new int[C * info[0][0] + 1];

		for (int i = 0; i < N; i++) {

			for (int j = info[i][0]; j < dp.length; j++) {
				dp[j] = Math.max(dp[j], dp[j - info[i][0]] + info[i][1]);
			}

		}


		for (int i = 1; i < dp.length; i++) {
			if (dp[i] >= C) {
				System.out.println(i);
				return;
			}
		}

	}
}
