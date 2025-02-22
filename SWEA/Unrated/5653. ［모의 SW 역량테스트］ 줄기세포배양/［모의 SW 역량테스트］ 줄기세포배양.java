import java.io.*;
import java.util.*;

public class Solution {

	private static int R, C, K;
	private static int[][] map;
	private static ArrayList<int[]> cell;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			K = Integer.parseInt(st.nextToken());

			R = N + (K * 2);
			C = M + (K * 2);
			map = new int[R][C];
			cell = new ArrayList<>();

			int sr = R / 2;
			int sc = C / 2;

			for (int r = sr; r < sr + N; r++) {

				st = new StringTokenizer(br.readLine());

				for (int c = sc; c < sc + M; c++) {

					map[r][c] = Integer.parseInt(st.nextToken());

					if (map[r][c] > 0) {
						cell.add(new int[] { r, c, map[r][c], map[r][c], map[r][c] });
					}

				}

			}

			sortArr();
			culture();

			int cnt = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] >= 1)
						cnt++;
				}
			}

			sb.append("#" + t + " ").append(cnt).append("\n");

		}

		System.out.println(sb.toString());

	}

	private static void culture() {

		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < cell.size(); i++) {
			q.offer(cell.get(i));
		}

		for (int t = 0; t < K; t++) {

			int size = q.size();

			for (int k = 0; k < size; k++) {

				int[] curr = q.poll();

				if (curr[2] != 0) {
					curr[2]--;
					q.offer(curr);

				} else {

					curr[3]--;
					
					if (curr[3] > 0) {
						q.offer(curr);
					} else {
						map[curr[0]][curr[1]] = -1;
					}

					for (int i = 0; i < 4; i++) {

						int nr = curr[0] + delta[i][0];
						int nc = curr[1] + delta[i][1];

						if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 0) {

							map[nr][nc] = curr[4];
							q.offer(new int[] { nr, nc, map[nr][nc], map[nr][nc], map[nr][nc] });
						}
					}

				}


			}

		}

	}

	private static void sortArr() {

		Collections.sort(cell, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[4] - o1[4];
			}

		});

	}

}
