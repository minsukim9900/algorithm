import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, K;
	private static List<int[]>[][] map;
	private static int[][] delta = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };

	private static ArrayList<int[]> fireball = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			fireball.add(new int[] { r, c, m, s, d });
		}

		for (int i = 0; i < K; i++) {
			move();
			spreed();
		}

		int sum = 0;
		for (int i = 0; i < fireball.size(); i++) {
			sum += fireball.get(i)[2];
		}
		System.out.println(sum);
	}

	private static void move() {

		for (int i = 0; i < fireball.size(); i++) {
			int[] curr = fireball.get(i);

			int r = (curr[0] + (delta[curr[4]][0] * curr[3])) % N;
			int c = (curr[1] + (delta[curr[4]][1] * curr[3])) % N;

			if (r < 0)
				r += N;
			else if (r >= N)
				r %= N;
			if (c < 0)
				c += N;
			else if (c >= N)
				c %= N;
            
            curr[0] = r;
            curr[1] = c;
            
			map[curr[0]][curr[1]].add(curr);

		}

	}

	private static void spreed() {

		fireball = new ArrayList<>();

		for (int r = 0; r < N; r++) {

			for (int c = 0; c < N; c++) {
				if (map[r][c].size() == 0) {
					continue;
				} else if (map[r][c].size() >= 2) {

					int mass = 0, speed = 0;
					int odd = 0, even = 0;

					for (int i = 0; i < map[r][c].size(); i++) {
						int[] curr = map[r][c].get(i);

						if (curr[4] % 2 == 0) {
							even++;
						} else {
							odd++;
						}

						mass += curr[2];
						speed += curr[3];

					}

					mass /= 5;
					if (mass <= 0) {
						map[r][c].clear();
						continue;
					}

					speed /= map[r][c].size();

					if (even == map[r][c].size() || odd == map[r][c].size()) {
						for (int i = 0; i < 8; i += 2) {
							fireball.add(new int[] { r, c, mass, speed, i });
						}
					} else {
						for (int i = 1; i < 8; i += 2) {
							fireball.add(new int[] { r, c, mass, speed, i });
						}
					}

					map[r][c].clear();

				} else if (map[r][c].size() > 0) {
					int[] curr = map[r][c].get(0);
					fireball.add(curr);
					map[r][c].clear();

				}

			}

		}

	}

}