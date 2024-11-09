import java.io.*;
import java.util.*;

public class Main {

	private static int[][] field;
	private static boolean[][] check;
	private static int N, M, napa;
	private static int count;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			napa = Integer.parseInt(st.nextToken());
			field = new int[N][M];
			check = new boolean[N][M];
			count = 0;

			for (int i = 0; i < napa; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				field[r][c] = 1;
			}
			
			for(int r = 0; r<N; r++){
				for(int c = 0; c<M; c++) {
					if(!check[r][c] && field[r][c] == 1) {
						dfs(r,c);
						count++;
					}
				}
			}
			
			sb.append(count).append("\n");

		}
		
		System.out.println(sb.toString());

	}

	public static void dfs(int r, int c) {
		check[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >=0 && nr<N && nc >= 0 && nc<M) {
				int num = field[nr][nc];
				if(!check[nr][nc] && num == 1) {
					dfs(nr,nc);
				}
			}
			
		}

	}

}
