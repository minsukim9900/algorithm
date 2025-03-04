import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M, R, C, L;
	private static int[][] map;
	private static int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	private static Set<Integer>[] ternal = new HashSet[4];
	private static boolean[][] visited;

	static {
		
		for(int i = 0; i<4; i++) {
			ternal[i] = new HashSet<>();
		}
		
		ternal[0].add(1);
		ternal[0].add(2);
		ternal[0].add(5);
		ternal[0].add(6);
		ternal[1].add(1);
		ternal[1].add(3);
		ternal[1].add(4);
		ternal[1].add(5);
		ternal[2].add(1);
		ternal[2].add(2);
		ternal[2].add(4);
		ternal[2].add(7);
		ternal[3].add(1);
		ternal[3].add(3);
		ternal[3].add(6);
		ternal[3].add(7);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			sb.append("#" + t + " " + bfs() + "\n");

		}
		
		System.out.println(sb.toString());

	}

	private static int bfs() {

		Queue<int[]> q = new ArrayDeque<>();
		visited[R][C] = true;

		q.add(new int[] { R, C, map[R][C] });
		int cnt = 0;
		for (int i = 0; i < L; i++) {
			
			
			
			int size = q.size();
			cnt += size;
			for (int j = 0; j < size; j++) {
				
				int[] curr = q.poll();
				int[] posDir = selectDirect(curr[2]);

				for (int k = 0; k < posDir.length; k++) {

					int nr = curr[0] + delta[posDir[k]][0];
					int nc = curr[1] + delta[posDir[k]][1];

					if (nr >= 0 && nr < N && nc >= 0 && nc < M && ternal[posDir[k]].contains(map[nr][nc])
							&& !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc, map[nr][nc]});
					}
					
				}

			}

		}
		
		return cnt;

	}

	private static int[] selectDirect(int ternal) {
		int[] arr = null;

		switch (ternal) {
		// 상 좌 하 우;
		case 1:
			arr = new int[] { 0, 1, 2, 3 };
			break;
		case 2:
			arr = new int[] { 0, 2 };
			break;
		case 3:
			arr = new int[] { 1, 3 };
			break;
		case 4:
			arr = new int[] { 0, 3 };
			break;
		case 5:
			arr = new int[] { 2, 3 };
			break;
		case 6:
			arr = new int[] { 1, 2 };
			break;
		case 7:
			arr = new int[] { 0, 1 };
			break;

		}

		return arr;

	}

}
