import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[][] nums;
	private static int[][] prefix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		nums = new int[N][N];
		prefix = new int[N + 1][N + 1];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				nums[r][c] = str.charAt(c) - '0';
			}
		}

		for (int r = 1; r <= N; r++) {
			int sum = 0;
			for (int c = 1; c <= N; c++) {
				sum += nums[r - 1][c - 1];
				prefix[r][c] = prefix[r - 1][c] + sum;
			}
		}
		search(0, 0, N);
		System.out.println(sb.toString());
	}

	private static StringBuilder sb = new StringBuilder();

	private static void search(int sr, int sc, int size) {
		if (size == 0) {
			return;
		}

		int er = sr + size - 1;
		int ec = sc + size - 1;
		int count = cal(sr, sc, er, ec);

		if (count == 0) {
			sb.append("0");
		} else if (count > 0 && count == size * size) {
			sb.append("1");
		} else {
			int half = size / 2;

			sb.append("(");
			search(sr, sc, size / 2);
			search(sr, sc + half, size / 2);
			search(sr + half, sc, size / 2);
			search(sr + half, sc + half, size / 2);
			sb.append(")");
		}
	}

	private static int cal(int sr, int sc, int er, int ec) {
		return prefix[er + 1][ec + 1] - (prefix[er + 1][sc] + prefix[sr][ec + 1]) + prefix[sr][sc];
	}
}