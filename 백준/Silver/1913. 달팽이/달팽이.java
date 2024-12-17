import java.io.*;
import java.util.*;

public class Main {

	private static int N, findNum;
	private static int[] dx = { 1, 0, -1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static int[][] board;
	private static int[] result = new int[2];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		findNum = Integer.parseInt(br.readLine());
		
		board = new int[N+2][N+2];
		int nums = N * N;
		int boundary = N;
		int dir = 0;
		int r = 0, c = 1;
		
		
		
			while(nums != 0) {
				
				for(int i = 0; i<boundary; i++) {
					r += dx[dir];
					board[r][c] = nums--;
					if(board[r][c] == findNum) {
						result[0] = r;
						result[1] = c;
					}
				}
				
				boundary--;
				dir = (dir + 1 ) % 4;
				
				for(int i = 0; i<boundary; i++) {
					c += dy[dir];
					board[r][c] = nums--;
					if(board[r][c] == findNum) {
						result[0] = r;
						result[1] = c;
					}
				}
				dir = (dir + 1 ) % 4;
				
				
				
			}
			
			
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		sb.append(result[0]).append(" ").append(result[1]);
		System.out.println(sb.toString());
	}

}
