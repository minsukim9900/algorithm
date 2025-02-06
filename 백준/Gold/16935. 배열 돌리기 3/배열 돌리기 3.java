import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, R;
	private static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int order = Integer.parseInt(st.nextToken());
			N = map.length;
			M = map[0].length;

			switch (order) {
			case 1: {
				first();

				break;
			}

			case 2: {
				second();
				break;
			}

			case 3: {
				map = third();
				break;
			}

			case 4: {
				map = fourth();
				break;
			}

			case 5: {
				fifth();
				break;
			}

			case 6: {
				sixth();
				break;
			}

			}

		}
		
		N = map.length;
		M = map[0].length;
		
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r<N; r++) {
			for(int c = 0; c<M; c++) {
				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

	private static void first() {

		for (int c = 0; c < M; c++) {
			int left = 0;
			int right = N - 1;
			while (left < right) {
				int tmp = map[left][c];
				map[left][c] = map[right][c];
				map[right][c] = tmp;
				left++;
				right--;
			}
		}

	}

	private static void second() {
		for (int r = 0; r < N; r++) {

			int left = 0;
			int right = M - 1;

			while (left < right) {

				int tmp = map[r][left];
				map[r][left] = map[r][right];
				map[r][right] = tmp;
				left++;
				right--;

			}
		}

	}

	private static int[][] third() {
		int[][] tmp = new int[M][N];

		for (int c = 0; c < M; c++) {
			for (int r = N - 1; r >= 0; r--) {
				tmp[c][(N - 1) - r] = map[r][c];
			}
		}

		return tmp;
	}

	private static int[][] fourth() {
		int[][] tmp = new int[M][N];

		for (int c = M - 1; c >= 0; c--) {
			for (int r = 0; r < N; r++) {
				tmp[(M - 1) - c][r] = map[r][c];
			}
		}

		return tmp;

	}

	private static void fifth() {

		for (int i = 0; i < 3; i++) {

			for (int r = 0; r < N / 2; r++) {

				for (int c = 0; c < M / 2; c++) {

					if (i == 0) {

						int tmp = map[r][c];
						map[r][c] = map[r][c + (M / 2)];
						map[r][c + (M / 2)] = tmp;

					} else if (i == 1) {

						int tmp = map[r][c];
						map[r][c] = map[r + (N / 2)][c + (M / 2)];
						map[r + (N / 2)][c + (M / 2)] = tmp;

					} else {

						int tmp = map[r][c];
						map[r][c] = map[r + (N / 2)][c];
						map[r + (N / 2)][c] = tmp;

					}

				}

			}

		}

	}

	private static void sixth() {

		for (int i = 0; i < 3; i++) {

			for (int r = 0; r < N / 2; r++) {

				for (int c = M / 2; c < M; c++) {

					if (i == 0) {

						int tmp = map[r][c];
						map[r][c] = map[r][(c + (M / 2)) % M];
						map[r][(c + (M / 2)) % M] = tmp;

					} else if (i == 1) {

						int tmp = map[r][c];
						map[r][c] = map[(r + (N / 2)) % N][(c + (M / 2)) % M];
						map[(r + (N / 2)) % N][(c + (M / 2)) % M] = tmp;

					} else {

						int tmp = map[r][c];
						map[r][c] = map[(r + (N / 2)) % N][c];
						map[(r + (N / 2)) % N][c] = tmp;

					}

				}

			}

		}
	}

}
