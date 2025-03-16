import java.io.*;
import java.util.*;

public class Main {

	private static boolean[][] map = new boolean[101][101];

	private static int[][] delta = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());
			ArrayList<Integer> info = makeGeneration(dir, gen);

			visit(r, c, info);
		}
		int cnt = 0;
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (map[r][c] && map[r + 1][c] && map[r][c + 1] && map[r + 1][c + 1])
					cnt++;
			}
		}

		System.out.println(cnt);

	}

	private static void visit(int r, int c, ArrayList<Integer> info) {

		map[r][c] = true;
		int nr = r;
		int nc = c;

		for (int i = 0; i < info.size(); i++) {
			nr += delta[info.get(i)][0];
			nc += delta[info.get(i)][1];
			map[nr][nc] = true;
		}

	}


	private static ArrayList<Integer> makeGeneration(int dir, int gen) {
		ArrayList<Integer> info = new ArrayList<>();
		info.add(dir);

		for (int g = 0; g < gen; g++) {

			int size = info.size();

			for (int i = size - 1; i >= 0; i--) {
				int nd = (info.get(i) + 1) & 3;
				info.add(nd);
			}

		}

		return info;
	}
}
