import java.io.*;
import java.util.*;

public class Main {
	private static int[] dr = { -1, 0, 1, 0 };
	private static int[] dc = { 0, -1, 0, 1 };
	private static int N, M, sr, sc, curr_dir;
	private static int[][] room;
	private static int cnt;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		curr_dir = Integer.parseInt(st.nextToken());
		if(curr_dir == 1 || curr_dir == 3) {
			curr_dir = (curr_dir + 2) % 4;
		}
		room = new int[N][M];

		cnt = 1;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		room[sr][sc] = 2;
		clean(sr, sc, curr_dir);
		System.out.println(cnt);

	}

	private static void clean(int r, int c, int dir) {
		for (int i = 0; i < 4; i++) {
			
			dir = (dir + 1) % 4;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				
				if (room[nr][nc] == 0) {
					room[nr][nc] = 2;
					cnt++;
					clean(nr, nc, dir);
					return;
				}
				
			}
			
		}
		
		int br = r - dr[dir];
		int bc = c - dc[dir];
		
		if (br >= 0 && br < N && bc >= 0 && bc < M && room[br][bc] != 1) {
			clean(br, bc, dir);
			
		}

	}


}
