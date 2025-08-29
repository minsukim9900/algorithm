import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] prefix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prefix = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int num = Integer.parseInt(st.nextToken());
				sum += num;
				prefix[r][c] = prefix[r - 1][c] + sum;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());

			sb.append(prefix[er][ec] - (prefix[er][sc - 1] + prefix[sr - 1][ec]) + prefix[sr - 1][sc - 1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}