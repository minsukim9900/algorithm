import java.io.*;
import java.util.*;

public class Solution {

	private static int N, max;
	private static int[][] delta = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			max = 0;

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			for (int r = 0; r < N; r++) {

				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < N; r++) {

				for (int c = 0; c < N; c++) {
					
					if(r == 0 && c== 0) continue;
					if(r == N-1 && c == 0) continue;
					if(r == 0 && c == N-1) continue;
					if(r == N-1 && c == N-1) continue;
					
					visited = new boolean[N][N];
					visited[r][c] = true;
					int[] curr = new int[] { r, c };
					boolean[] visitNum = new boolean[101];
					visitNum[map[r][c]] = true;
					dfs(curr, curr, visitNum, new boolean[4], -1, 0);

				}
			}
			
			if(max == 0) max = -1;
			
			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int[] curr, int[] p, boolean[] visitNum, boolean[] visitDir, int preDir, int cnt) {
		
		
		if (cntVisitDir(visitDir) >= 2 && curr[0] == p[0] && curr[1] == p[1]) {


			max = Math.max(max, cnt + 2);
			
		} else {

			if (cntVisitDir(visitDir) == 2) {
				
				visited[p[0]][p[1]] = false;
			}

			for (int i = 0; i < 4; i++) {

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visitDir[i] && !visited[nr][nc]) {
					if (nr == p[0] && nc == p[1]) {

						dfs(new int[] { nr, nc }, p, visitNum, visitDir, i, cnt - 1);

					} else {

						if (!visitNum[map[nr][nc]]) {

							if (preDir != -1 && preDir != i) {
								visitDir[preDir] = true;
							}
							visited[nr][nc] = true;
							visitNum[map[nr][nc]] = true;

							dfs(new int[] { nr, nc }, p, visitNum, visitDir, i, cnt + 1);

							if (preDir != -1) {
								visitDir[preDir] = false;
							}
							visited[nr][nc] = false;
							visitNum[map[nr][nc]] = false;

						}
					}

				}

			}

		}

	}

	private static int cntVisitDir(boolean[] visitDir) {

		int cnt = 0;
		for (boolean b : visitDir) {
			if (b)
				cnt++;
		}

		return cnt;
	}

}
