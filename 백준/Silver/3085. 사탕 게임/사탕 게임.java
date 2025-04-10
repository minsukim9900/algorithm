import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static char[][] boni;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		boni = new char[N][N];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				char tmp = str.charAt(c);
				boni[r][c] = tmp;
			}
		}

		int max = cntSame();


		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N-1; c++) {
				if(boni[r][c] == boni[r][c+1]) continue;
				swapRow(r, c);
				int tmp = cntSame();
				max = Math.max(max, tmp);
				swapRow(r, c);
			}
			if (max == N) {
				System.out.println(N);
				return;
			}
		}

		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N-1; r++) {
				if(boni[r][c] == boni[r+1][c]) continue;
				swapColumn(r, c);
				int tmp = cntSame();
				max = Math.max(max, tmp);
				swapColumn(r, c);
			}
			if (max == N) {
				System.out.println(N);
				return;
			}
		}

		System.out.println(max);

	}

	public static void swapRow(int r, int c) {
		char tmp = boni[r][c];
		boni[r][c] = boni[r][c + 1];
		boni[r][c + 1] = tmp;
	}

	public static void swapColumn(int r, int c) {
		char tmp = boni[r][c];
		boni[r][c] = boni[r + 1][c];
		boni[r + 1][c] = tmp;
	}

	public static int cntSame() {
		int max = 0;

		for (int r = 0; r < N; r++) {
			int cnt = 1;
			for (int c = 0; c < N - 1; c++) {
				if (boni[r][c] == boni[r][c + 1]) {
					cnt++;
				} else {
					cnt = 1;
				}
				max = Math.max(max, cnt);
			}
		}

		for (int c = 0; c < N; c++) {
			int cnt = 1;
			for (int r = 0; r < N - 1; r++) {
				if (boni[r][c] == boni[r + 1][c]) {
					cnt++;
				} else {
					cnt = 1;
				}
				max = Math.max(max, cnt);
			}
		}

		return max;
	}


}
