import java.io.*;
import java.util.*;

public class Solution {

	private static int N, max;
	private static int[][] board;
	private static boolean isVisit;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static ArrayList<int[]>[] hole;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			max = 0;
			board = new int[N][N];
			hole = new ArrayList[5];
			
			for (int i = 0; i < 5; i++) {
				hole[i] = new ArrayList<>();
			}

			ArrayList<int[]> loc = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());

					if (board[r][c] > 5) {
						hole[board[r][c] - 6].add(new int[] { r, c });
					}
					if (board[r][c] == 0) {
						loc.add(new int[] { r, c });
					}
				}
			}

			for (int[] w : loc) {
				for (int i = 0; i < 4; i++) {
					isVisit = false;
					dfs(w[0], w[1], i, w.clone(), 0);
				}
			}
			
			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, int dir, int[] st, int cnt) {
		

		if (r == st[0] && c == st[1] && isVisit) {
			max = Math.max(max, cnt);
			return;
		}
		
		if(r == st[0] && c == st[1] && !isVisit) {
			isVisit = true;
		}
		
		if (r < 0 || r >= N || c < 0 || c >= N) {
			dfs(r - delta[dir][0], c - delta[dir][1], visitWall(dir), st, cnt + 1);
			return;
		}

		if (board[r][c] == -1) {
			max = Math.max(max, cnt);
			return;
		}


		int nd = dir;
		if (board[r][c] >= 1 && board[r][c] <= 5) {
			nd = changeDir(board[r][c], nd);
			dfs(r + delta[nd][0], c + delta[nd][1], nd, st, cnt + 1);
			return;
		}

		if (board[r][c] >= 6 && board[r][c] <= 10) {
			int[] next = potal(r, c);
			dfs(next[0] + delta[dir][0], next[1] +delta[dir][1], dir, st, cnt);
			return;
		}
		
		dfs(r + delta[dir][0], c+ delta[dir][1] , dir, st, cnt);
		return;

	}

	private static int visitWall(int dir) {
		if (dir == 0)
			return 1;
		if (dir == 1)
			return 0;
		if (dir == 2)
			return 3;
		return 2;
	}

	private static int[] potal(int r, int c) {
		int[] location = null;
		for (int i = 0; i < 2; i++) {
			int[] curr = hole[board[r][c] - 6].get(i);
			if (curr[0] == r && curr[1] == c) {
				location = hole[board[r][c] - 6].get((i + 1) & 1);
			}
		}

		return location;

	}

	private static int changeDir(int block, int dir) {

		switch (block) {
		case 1: {
			if (dir == 1)
				return 3;
			else if (dir == 2)
				return 0;
			else if (dir == 3)
				return 2;
			else
				return 1;
		}
		case 2: {
			if (dir == 1)
				return 0;
			else if (dir == 0)
				return 3;
			else if (dir == 2)
				return 1;
			else
				return 2;
		}
		case 3: {
			if (dir == 0)
				return 2;
			else if (dir == 1)
				return 0;
			else if (dir == 2)
				return 3;
			else
				return 1;
		}
		case 4: {
			if (dir == 0)
				return 1;
			else if (dir == 1)
				return 2;
			else if (dir == 2)
				return 3;
			else
				return 0;
		}
		case 5: {
			if (dir == 0)
				return 1;
			else if (dir == 1)
				return 0;
			else if (dir == 2)
				return 3;
			return 2;
		}
		}

		return 0;

	}

}