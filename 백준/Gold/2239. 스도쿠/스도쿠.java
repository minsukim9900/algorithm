import java.io.*;
import java.util.*;

public class Main {

	private static int[][] map, boxVisited;
	private static int[] rVisited, cVisited;
	private static int N;
	private static List<int[]> adj = new ArrayList<>();
	private static boolean isComplete;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = 9;
		map = new int[N][N];
		rVisited = new int[N];
		cVisited = new int[N];
		boxVisited = new int[3][3];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = str.charAt(c) - '0';

				if (map[r][c] != 0) {
					rVisited[r] |= (1 << map[r][c]);
				} else {
					adj.add(new int[] { r, c });
				}
			}
		}

		init();
		sudo(0);

	}

	private static void sudo(int depth) {

		if (depth == adj.size()) {
			output();
			isComplete = true;
			System.out.println(sb);
		} else {

			int[] curr = adj.get(depth);

			for (int i = 1; i <= 9; i++) {
				if (!isComplete) {
					if (isCheck(curr[0], curr[1], i)) {
						visiteNum(curr[0], curr[1], i);
						map[curr[0]][curr[1]] = i;
						sudo(depth + 1);
						map[curr[0]][curr[1]] = 0;
						unvisiteNum(curr[0], curr[1], i);
					}
				}

			}
		}

	}

	private static void output() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
	}

	private static void visiteNum(int r, int c, int value) {
		rVisited[r] |= (1 << value);
		cVisited[c] |= (1 << value);
		boxVisited[r / 3][c / 3] |= (1 << value);
	}

	private static void unvisiteNum(int r, int c, int value) {
		rVisited[r] ^= (1 << value);
		cVisited[c] ^= (1 << value);
		boxVisited[r / 3][c / 3] ^= (1 << value);
	}

	private static boolean isCheck(int r, int c, int value) {

		if ((rVisited[r] & (1 << value)) != 0) {
			return false;
		}
		if ((cVisited[c] & (1 << value)) != 0) {
			return false;
		}
		if ((boxVisited[r / 3][c / 3] & (1 << value)) != 0) {
			return false;
		}

		return true;

	}

	private static void init() {

		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {

				if (map[r][c] != 0) {
					cVisited[c] |= (1 << map[r][c]);
				}

			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int nr = r / 3;
				int nc = c / 3;
				if (map[r][c] != 0) {
					boxVisited[nr][nc] |= (1 << map[r][c]);
				}

			}
		}
	}

}
