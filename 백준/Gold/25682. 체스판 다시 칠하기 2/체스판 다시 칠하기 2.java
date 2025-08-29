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
		int[][][] prefix = new int[2][N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			for (int c = 1; c <= M; c++) {
				chess[r][c] = str.charAt(c - 1);
			}
		}

		for (int r = 1; r <= N; r++) {
			char comp = 'B';
			if (r % 2 == 1) {
				comp = 'W';
			}
			int sum = 0;
			for (int c = 1; c <= M; c++) {
				int num = 0;
				if (chess[r][c] != comp) {
					num++;
				}
				sum += num;
				prefix[0][r][c] = prefix[0][r - 1][c] + sum;
				comp = changeState(comp);
			}
		}

		for (int r = 1; r <= N; r++) {
			char comp = 'W';
			if (r % 2 == 1) {
				comp = 'B';
			}
			int sum = 0;
			for (int c = 1; c <= M; c++) {
				int num = 0;
				if (chess[r][c] != comp) {
					num++;
				}
				sum += num;
				prefix[1][r][c] = prefix[1][r - 1][c] + sum;
				comp = changeState(comp);
			}
		}

		int answer = Integer.MAX_VALUE;

		for (int er = K; er <= N; er++) {
			for (int ec = K; ec <= M; ec++) {
				int sr = er - K + 1;
				int sc = ec - K + 1;
				int cal1 = prefix[0][er][ec] - (prefix[0][er][sc - 1] + prefix[0][sr - 1][ec])
						+ prefix[0][sr - 1][sc - 1];
				int cal2 = prefix[1][er][ec] - (prefix[1][er][sc - 1] + prefix[1][sr - 1][ec])
						+ prefix[1][sr - 1][sc - 1];
				answer = Math.min(answer, Math.min(cal1, cal2));
			}
		}
		System.out.println(answer);
	}

	private static char changeState(char c) {
		return c == 'W' ? 'B' : 'W';
	}
}