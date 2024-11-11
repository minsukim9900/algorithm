import java.io.*;
import java.util.*;

public class Main {

	private static char[][] room;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		room = new char[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				room[i][j] = c;
			}
		}

		int cntR = cntRow();
		int cntC = cntColumn();

		System.out.println(cntR + " " + cntC);

	}

	public static int cntRow() {
		int sum = 0;
		for (int r = 0; r < N; r++) {
			int cnt = 0;
			for (int c = 0; c < N; c++) {
				if (room[r][c] == '.') {
					cnt++;
				} else {
					if (cnt >= 2) {
						sum++;
					}
					cnt = 0;
				}
			}
			if(cnt >= 2) {
				sum++;
			}
		}
		return sum;

	}

	public static int cntColumn() {
		int sum = 0;
		for (int r = 0; r < N; r++) {
			int cnt = 0;
			for (int c = 0; c < N; c++) {
				if (room[c][r] == '.') {
					cnt++;

				} else {
					if(cnt >= 2) {
						sum++;
					}
					cnt = 0;
				}
			}
			if(cnt >= 2) {
				sum++;
			}
		}
		return sum;
	}

}
