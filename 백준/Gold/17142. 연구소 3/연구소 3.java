import java.io.*;
import java.util.*;

public class Main {

	private static int minTime = 987654321;
	private static int N, M;
	private static int[][] map;
	private static ArrayList<int[]> virus = new ArrayList<>();
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		int zeroCnt = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 0)
					zeroCnt++;

				if (map[r][c] == 2) {
					virus.add(new int[] { r, c });
					map[r][c] = -1;
				}

			}
		}

		if (zeroCnt == 0) {
			System.out.println(0);
			return;
		}

		combi(0, 0, new int[M], zeroCnt);
		System.out.println(minTime == 987654321 ? -1 : minTime);

	}

	private static void combi(int idx, int depth, int[] result, int zeroCnt) {

		if (depth == M) {

			bfs(new int[N][N], result, zeroCnt);

		} else {

			for (int i = idx; i < virus.size(); i++) {
				result[depth] = i;
				combi(i + 1, depth + 1, result, zeroCnt);
			}

		}

	}

	private static void bfs(int[][] copyMap, int[] select, int zeroCnt) {

		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, N);
		}

		Queue<int[]> q = new ArrayDeque<>();

		for (int s : select) {
			int[] curr = virus.get(s);
			q.offer(new int[] { curr[0], curr[1], 0 });
			copyMap[curr[0]][curr[1]] = -2;
		}

		int time = 0;

		while (!q.isEmpty()) {

			int[] curr = q.poll();

			if (time >= minTime)
				return;

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && (copyMap[nr][nc] == 0 || copyMap[nr][nc] == -1)) {

					if (copyMap[nr][nc] == 0) {
						zeroCnt--;
						time = Math.max(time, curr[2] + 1);
					}
					copyMap[nr][nc] = curr[2] + 1;
					q.offer(new int[] { nr, nc, copyMap[nr][nc] });

				}

			}

		}

		if (zeroCnt == 0)
			minTime = Math.min(minTime, time);

	}
}
