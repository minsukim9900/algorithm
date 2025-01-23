import java.io.*;
import java.util.*;

public class Main {

	private static int R, C;
	private static char[][] map;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R + 2][C + 2];
		board = new int[R + 2][C + 2];

		ArrayList<int[]> island = new ArrayList<>();

		for (int r = 0; r < R + 2; r++) {
			map[r][0] = '.';
			map[r][C + 1] = '.';
		}
		for (int c = 0; c < C + 2; c++) {
			map[0][c] = '.';
			map[R + 1][c] = '.';
		}

		for (int r = 1; r <= R; r++) {

			String state = br.readLine();

			for (int c = 1; c <= C; c++) {

				char tmp = state.charAt(c - 1);
				map[r][c] = tmp;
				island.add(new int[] { r, c });

			}
		}

		bfs(island);

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (map[r][c] == '.') {
					board[r][c] = 0;
				} else {
					board[r][c] = 1;
				}
			}
		}

		int startR = R;
		int startC = C;
		int endR = 1;
		int endC = 1;

		for (int r = 1; r <= R; r++) {
			int sum = 0;
			for (int c = 1; c <= C; c++) {
				sum += board[r][c];
			}

			if (sum >= 1) {
				startR = Math.min(startR, r);
				endR = Math.max(endR, r);
			}
		}

		for (int c = 1; c <= C; c++) {
			int sum = 0;
			for (int r = 1; r <= R; r++) {
				sum += board[r][c];
			}
			
			if (sum >= 1) {
				startC = Math.min(startC, c);
				endC = Math.max(endC, c);
			}
		}
		
		for(int r = startR; r<=endR; r++) {
			for(int c = startC; c<=endC; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}

	}

	private static void bfs(ArrayList<int[]> island) {

		ArrayList<int[]> change = new ArrayList<>();

		for (int i = 0; i < island.size(); i++) {
			int[] curr = island.get(i);
			int cnt = 0;

			for (int j = 0; j < 4; j++) {
				int nr = curr[0] + delta[j][0];
				int nc = curr[1] + delta[j][1];

				if (map[nr][nc] == '.') {
					cnt++;
				}

			}
			if (cnt >= 3) {
				change.add(new int[] { curr[0], curr[1] });
			}
		}

		changeIsland(change);

	}

	private static void changeIsland(ArrayList<int[]> change) {

		for (int i = 0; i < change.size(); i++) {
			int[] curr = change.get(i);

			map[curr[0]][curr[1]] = '.';
		}

	}

}