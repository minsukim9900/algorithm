import java.io.*;
import java.util.*;

public class Solution {

	private static int T, N, M;
	private static int[][] room;
	private static int maxNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			maxNum = Integer.MIN_VALUE;
			room = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					room[r][c] = Integer.parseInt(st.nextToken());
				}

			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					process(r, c);
				}
			}
			System.out.println("#"+t+" "+maxNum);

		}
	}

	public static void process(int r, int c) {
		int sum = 0;
		if(r + M > N || c +M > N) return;
		for (int i = r; i < r+M; i++) {
			for (int j = c; j < c+M; j++) {
				sum += room[i][j];
			}
		}

		maxNum = Math.max(maxNum, sum);
	}

}
