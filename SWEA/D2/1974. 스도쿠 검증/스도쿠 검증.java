import java.io.*;
import java.util.*;

public class Solution {

	static boolean check(int[][] sudoku) {

		for (int r = 0; r < 9; r++) {
			boolean[] number = new boolean[10];
			for (int c = 0; c < 9; c++) {
				int num = sudoku[r][c];
				if (number[num]) {
					return false;
				}
				number[num] = true;
			}
		}

		for (int c = 0; c < 9; c++) {
			boolean[] number = new boolean[10];
			for (int r = 0; r < 9; r++) {
				int num = sudoku[r][c];
				if (number[num]) {
					return false;
				}
				number[num] = true;
			}
		}

		boolean[][][] number = new boolean[3][3][10];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int num = sudoku[i][j];
				int r = i / 3;
				int c = j / 3;
				if (number[r][c][num]) {
					return false;
				}
				number[r][c][num] = true;
			}
		}

		return true;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {

			int[][] sudoku = new int[9][9];

			for (int r = 0; r < 9; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 9; c++) {
					sudoku[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;
			if (check(sudoku)) {
				result = 1;
			}

			System.out.println("#" + (t + 1) + " " + result);

		}
	}
}