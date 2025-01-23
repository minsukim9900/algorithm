import java.io.*;
import java.util.*;

public class Solution {

	private static int H, W;
	private static int[][] map;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new int[H][W];

			int[] tank = null;

			for (int r = 0; r < H; r++) {
				String state = br.readLine();
				for (int c = 0; c < W; c++) {

					char tmp = state.charAt(c);

					int ele = changeState(tmp);

					if (ele == -1) {
						int side = tankSide(tmp);
						tank = new int[] { r, c, side };
						map[r][c] = side;
					} else {
						map[r][c] = ele;
					}

				}
			}

			int N = Integer.parseInt(br.readLine());
			String order = br.readLine();
			startGame(tank, order);

			
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(changeChar(map[i][j]));
				}
				sb.append("\n");
			}
			
			System.out.print(sb);

		}
	}

	private static void startGame(int[] tank, String order) {

		int r = tank[0];
		int c = tank[1];
		int side = tank[2];

		for (int i = 0; i < order.length(); i++) {
			char ele = order.charAt(i);

			int dir = translate(ele);
			if (dir == 4) {
				shoot(tank);
			} else {

				int nr = r + delta[dir][0];
				int nc = c + delta[dir][1];

				if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == 6) {
					int tmp = map[r][c];
					map[r][c] = map[nr][nc];
					map[nr][nc] = tmp;
					r = nr;
					c = nc;
				}

				map[r][c] = dir;
				tank = new int[] { r, c, dir };

			}

		}

	}

	private static void shoot(int[] tank) {

		int r = tank[0];
		int c = tank[1];
		int dir = tank[2];
		while (true) {

			r += delta[dir][0];
			c += delta[dir][1];

			if (r < 0 || r >= H || c < 0 || c >= W) {
				break;
			} else {

				if (map[r][c] == 7) {
					map[r][c] = 6;
					break;
				} else if (map[r][c] == 8)
					break;

			}

		}

	}

	private static int translate(char ele) {
		switch (ele) {
		case 'U':
			return 0;
		case 'D':
			return 1;
		case 'L':
			return 2;
		case 'R':
			return 3;
		}
		return 4;
	}

	private static int tankSide(char ele) {

		switch (ele) {
		case '^':
			return 0;
		case 'v':
			return 1;
		case '<':
			return 2;
		case '>':
			return 3;
		}
		return -1;

	}

	private static int changeState(char ele) {

		switch (ele) {
		case '.':
			return 6;
		case '*':
			return 7;
		case '#':
			return 8;
		case '-':
			return 9;
		}

		return -1;

	}
	
	private static char changeChar(int ele) {
		
		switch(ele) {
		case 0 : return '^';
		case 1 : return 'v';
		case 2 : return '<';
		case 3 : return '>';
		case 6 : return '.';
		case 7 : return '*';
		case 8 : return '#';
		case 9 : return '-';
		
		
		}
		
		
		return ' ';
	}

}
