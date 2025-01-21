import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, cnt;
	private static int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	private static int[][] room;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];

		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		if(dir == 1 || dir == 3) {
			dir = (dir + 2) % 4;
		}
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		room[sr][sc] = 2;
		cnt = 1;
		clean(sr, sc, dir);

		System.out.println(cnt);
	}

	private static void clean(int r, int c, int dir) {
		
		for (int i = 0; i < 4; i++) {
			dir = (dir + 1) % 4;

			int nr = r + delta[dir][0];
			int nc = c + delta[dir][1];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] == 0) {
				room[nr][nc] = 2;
				cnt++;
				clean(nr, nc, dir);
				return;
			}

		}

		int br = r - delta[dir][0];
		int bc = c - delta[dir][1];

		if (br >= 0 && br < N && bc >= 0 && bc < M && room[br][bc] != 1) {
			clean(br, bc, dir);
		}

	}
}