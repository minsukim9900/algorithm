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

			map = new int[N + (2 * K)][M + (2 * K)];
			R = N + (2 * K);
			C = M + (2 * K);
			cell = new ArrayList<>();

			int sr = (N + (2 * K)) / 2;
			int sc = (M + (2 * K)) / 2;

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

			sb.append("#"+t+" ").append(cnt).append("\n");

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
			int s = 0;
			while (s < size) {
				s++;
				int[] curr = q.poll();

				if (curr[2] != 0) {
					q.offer(curr);
				} else {
					
					int tmp = curr[3];
					tmp--;
					
					if (tmp > 0) {
						q.offer(new int[] { curr[0], curr[1], curr[2], tmp, curr[4] });
					}else {
						map[curr[0]][curr[1]] = -1;
					}
					
					for (int i = 0; i < 4; i++) {
						int nr = curr[0] + delta[i][0];
						int nc = curr[1] + delta[i][1];

						if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 0) {
							
							map[nr][nc] = curr[3];
							q.offer(new int[] { nr, nc, map[nr][nc], map[nr][nc], map[nr][nc] });
						}
					}

				}
				
				if (curr[2] != 0) {
					curr[2]--;
				}
				

			}


		}

	}

	private static void sortArr() {

		Collections.sort(cell, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2] - o1[2];
			}

		});

	}

}
