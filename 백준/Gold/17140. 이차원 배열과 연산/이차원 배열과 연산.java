import java.io.*;
import java.util.*;

public class Main {
	private static int R, C, K, maxR, maxC;
	private static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());

		maxR = 3;
		maxC = 3;
		board = new int[100][100];
		for (int r = 0; r < maxR; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < maxC; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int t = 0; t <= 100; t++) {
			if (board[R][C] == K) {
				System.out.println(t);
				return;
			}

			if (maxR >= maxC) {
				rowSort();
			} else {
				columnSort();
			}
		}
		System.out.println(-1);
	}

	private static void columnSort() {
		int length = 0;
		for (int c = 0; c < maxC; c++) {
			int[] number = new int[101];
			for (int r = 0; r < maxR; r++) {
				number[board[r][c]]++;
			}

			List<int[]> arr = new ArrayList<>();
			for (int n = 1; n <= 100; n++) {
				if (number[n] > 0) {
					arr.add(new int[] { n, number[n] });
				}
			}
			arr.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

			int idx = 0;
			for (int[] a : arr) {
				if (idx >= 100)
					break;
				board[idx++][c] = a[0];
				if (idx >= 100)
					break;
				board[idx++][c] = a[1];
			}

			for (int i = idx; i < 100; i++) {
				board[i][c] = 0;
			}

			length = Math.max(length, idx);
		}
		maxR = Math.min(length, 100);
	}

	private static void rowSort() {
		int length = 0;
		for (int r = 0; r < maxR; r++) {
			int[] number = new int[101];
			for (int c = 0; c < maxC; c++) {
				number[board[r][c]]++;
			}

			List<int[]> arr = new ArrayList<>();
			for (int n = 1; n <= 100; n++) {
				if (number[n] > 0) {
					arr.add(new int[] { n, number[n] });
				}
			}
			arr.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

			int idx = 0;
			for (int[] a : arr) {
				if (idx >= 100)
					break;
				board[r][idx++] = a[0];
				if (idx >= 100)
					break;
				board[r][idx++] = a[1];
			}

			for (int i = idx; i < 100; i++) {
				board[r][i] = 0;
			}

			length = Math.max(length, idx);
		}
		maxC = Math.min(length, 100);
	}
}