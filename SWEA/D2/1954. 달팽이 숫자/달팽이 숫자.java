import java.io.*;
import java.util.*;

public class Solution {

	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine());

			int c = -1, r = 0;
			int dir = 0;
			int[][] board = new int[N][N];
			int num = 1;
			int boundary = N;
			while (num <= (N * N)) {

				for (int i = 0; i < boundary; i++) {
					c += dc[dir];
					board[r][c] = num++;
				}

				boundary--;
				dir = (dir + 1) % 4;

				for (int i = 0; i < boundary; i++) {
					r += dr[dir];
					board[r][c] = num++;
				}

				dir = (dir + 1) % 4;

			}
			
			sb.append("#").append(t).append("\n");
			
			for(int i = 0; i<N; i++) {
				
				for(int j = 0; j<N; j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append("\n");
			}

		}
		
		System.out.println(sb.toString());

	}

}
