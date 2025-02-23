import java.io.*;
import java.util.*;

public class Solution {

	private static int N, R, C;
	private static int[][] map;
	private static int[][] tmpMap;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[] bomb;
	private static int cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			map = new int[R][C];
			bomb = new int[N];
			cnt = Integer.MAX_VALUE;

			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			sel(0);
			sb.append("#"+t+" ").append(cnt+"\n");
		}
		System.out.println(sb.toString());

	}

	private static void sel(int depth) {
		if (depth == N) {
			tmpMap = new int[R][C];

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					tmpMap[r][c] = map[r][c];
				}
			}

			cal();
			cnt = Math.min(cnt, cntMap());

		} else {
			for (int i = 0; i < C; i++) {
				bomb[depth] = i;
				sel(depth + 1);
			}
		}
	}

	private static void output() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(tmpMap[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static int cntMap() {
		int cnt = 0;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (tmpMap[r][c] != 0)
					cnt++;
			}
		}

		return cnt;
	}

	private static void cal() {

		for (int i = 0; i < N; i++) {

			boolean[][] visited = new boolean[R][C];

			int tmp = bomb[i];

			Queue<int[]> q = new ArrayDeque<>();
			ArrayList<int[]> wall = new ArrayList<>();

			for (int r = 0; r < R; r++) {

				if (tmpMap[r][tmp] != 0) {
					q.add(new int[] { r, tmp, tmpMap[r][tmp] });
					visited[r][tmp] = true;
					break;
				}

			}
			while (!q.isEmpty()) {

				int[] curr = q.poll();
				wall.add(curr);

				for (int j = 0; j < 4; j++) {

					int nr = curr[0];
					int nc = curr[1];

					for (int k = 0; k < curr[2] - 1; k++) {

						nr += delta[j][0];
						nc += delta[j][1];

						if (nr >= 0 && nr < R && nc >= 0 && nc < C && tmpMap[nr][nc] != 0 && !visited[nr][nc]) {
							visited[nr][nc] = true;
							q.offer(new int[] { nr, nc, tmpMap[nr][nc] });
						}

					}
				}

			}

			changeWall(wall);
			delZero();
			visited = new boolean[R][C];
		}

	}

	private static void changeWall(ArrayList<int[]> wall) {

		for (int i = 0; i < wall.size(); i++) {
			int[] curr = wall.get(i);

			tmpMap[curr[0]][curr[1]] = 0;
		}
	}

	private static void delZero() {

		for (int c = 0; c < C; c++) {

			for (int r = R - 1; r > 0; r--) {

				if (tmpMap[r][c] == 0) {

					for (int i = r - 1; i >= 0; i--) {
						
						if(tmpMap[i][c] != 0) {
							tmpMap[r][c] = tmpMap[i][c];
							tmpMap[i][c] = 0;
							break;
						}
					}

				}

			}

		}

	}

}
