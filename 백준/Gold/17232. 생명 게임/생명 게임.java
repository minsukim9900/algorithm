import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, T, K, a, b;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		int[][] prefix = new int[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			int sum = 0;
			for (int c = 1; c <= M; c++) {
				int num = str.charAt(c - 1) == '.' ? 0 : 1;
				sum += num;
				prefix[r][c] = prefix[r - 1][c] + sum;
			}
		}

		int[][] answer = new int[N + 1][M + 1];
		for (int t = 0; t < T; t++) {
			int[][] newPrefix = new int[N + 1][M + 1];
			int[][] temp = new int[N + 1][M + 1];

			for (int r = 1; r <= N; r++) {
				int sum = 0;

				for (int c = 1; c <= M; c++) {
					int num = simulate(r, c, prefix);
					temp[r][c] = num;
					sum += num;
					newPrefix[r][c] = newPrefix[r - 1][c] + sum;
				}
			}

			prefix = newPrefix;
			answer = temp;
		}
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				sb.append(answer[r][c] == 1 ? '*' : '.');
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void output(int[][] prefix) {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				System.out.print(prefix[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int simulate(int r, int c, int[][] prefix) {
		int sr = Math.max(1, r - K);
		int sc = Math.max(1, c - K);

		int er = Math.min(N, r + K);
		int ec = Math.min(M, c + K);

		int currState = prefix[r][c] - (prefix[r - 1][c] + prefix[r][c - 1]) + prefix[r - 1][c - 1];
		boolean flag = currState == 1;
		int count = prefix[er][ec] - (prefix[sr - 1][ec] + prefix[er][sc - 1]) + prefix[sr - 1][sc - 1] - currState;

		if (flag) {
			return (count >= a && count <= b) ? 1 : 0;
		} else {
			return (count > a && count <= b) ? 1 : 0;
		}
	}
}