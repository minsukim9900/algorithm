import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] prefix = new int[N + 1][M + 1];
		int max = Integer.MIN_VALUE;

		for (int r = 1; r <= N; r++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				int num = Integer.parseInt(st.nextToken());
				sum += num;
				prefix[r][c] = prefix[r - 1][c] + sum;
			}
		}

		for (int sr = 1; sr <= N; sr++) {
			for (int sc = 1; sc <= M; sc++) {
				for (int er = sr; er <= N; er++) {
					for (int ec = sc; ec <= M; ec++) {
						int value = prefix[er][ec] - (prefix[er][sc - 1] + prefix[sr - 1][ec]) + prefix[sr - 1][sc - 1];
						max = Math.max(max, value);
					}
				}
			}
		}

		System.out.println(max);
	}
}