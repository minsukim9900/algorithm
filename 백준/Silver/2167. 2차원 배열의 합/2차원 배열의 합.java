import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] nums;
	private static int[][] prefix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N + 1][M + 1];
		prefix = new int[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				nums[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 1; r <= N; r++) {
			int sum = 0;
			for (int c = 1; c <= M; c++) {
				sum += nums[r][c];
				prefix[r][c] = prefix[r - 1][c] + sum;
			}
		}

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			
			int total = prefix[er][ec];
			int minusValue = prefix[er][sc - 1] + prefix[sr-1][ec];
			int plusValue = prefix[sr - 1][sc -1];
			sb.append(total - minusValue + plusValue).append("\n");
		}
		System.out.println(sb.toString());
	}
}