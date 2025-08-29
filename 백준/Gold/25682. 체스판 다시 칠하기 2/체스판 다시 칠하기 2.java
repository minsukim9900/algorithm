import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static char[][] chess;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		chess = new char[N + 1][M + 1];
		int[][] prefix = new int[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			for (int c = 1; c <= M; c++) {
				chess[r][c] = str.charAt(c - 1);
			}
		}

		for (int r = 1; r <= N; r++) {
			int sum = 0;
			for (int c = 1; c <= M; c++) {
				int num = 0;
				if (chess[r][c] != (((r + c) % 2 == 0) ? 'W' : 'B')) {
					num++;
				}
				sum += num;
				prefix[r][c] = prefix[r - 1][c] + sum;
			}
		}

		int answer = Integer.MAX_VALUE;

		for (int er = K; er <= N; er++) {
			for (int ec = K; ec <= M; ec++) {
				int sr = er - K + 1;
				int sc = ec - K + 1;
				int cal1 = prefix[er][ec] - (prefix[er][sc - 1] + prefix[sr - 1][ec])
						+ prefix[sr - 1][sc - 1];
				int cal2 = K * K - cal1;
				answer = Math.min(answer, Math.min(cal1, cal2));
			}
		}
		System.out.println(answer);
	}
}