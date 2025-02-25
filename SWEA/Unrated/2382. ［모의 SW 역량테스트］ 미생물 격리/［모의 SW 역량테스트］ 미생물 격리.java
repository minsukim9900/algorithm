import java.io.*;
import java.util.*;

public class Solution {

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[][] map;
	private static int N, M, K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			Queue<int[]> micro = new ArrayDeque<>();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				map[r][c] = cnt;
				micro.add(new int[] { r, c, cnt, d });
			}
			Queue<int[]> q = bfs(micro);
			sb.append("#" + t + " ").append(cnt(q) + "\n");

		}

		System.out.println(sb.toString());

	}

	private static int cnt(Queue<int[]> q) {
		int sum = 0;

		while (!q.isEmpty()) {
			sum += q.poll()[2];
		}

		return sum;
	}

	private static Queue<int[]> bfs(Queue<int[]> q) {

		for (int t = 0; t < M; t++) {

			int size = q.size();

			ArrayList<int[]> ch = new ArrayList<>();
			for (int j = 0; j < size; j++) {

				int[] curr = q.poll();

				int r = curr[0];
				int c = curr[1];
				int nr = curr[0] + delta[curr[3] - 1][0];
				int nc = curr[1] + delta[curr[3] - 1][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {

					if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) {
						int value = map[r][c] / 2;
						map[r][c] = 0;

						if (value != 0) {
							ch.add(new int[] { nr, nc, value, redir(curr[3])});
						}

					} else { 
						int value = map[r][c];
						map[r][c] = 0;
						ch.add(new int[] { nr, nc, value, curr[3] });

					}

				}

			}

			ArrayList<int[]> c = after(ch);

			for (int[] w : c) {
				q.offer(w);
			}

		}

		return q;

	}

	private static ArrayList<int[]> after(ArrayList<int[]> s) {

		int[][] vMap = new int[N][N];
		int[][] tmpDir = new int[N][N];
		int[][]	max = new int[N][N];

		for (int[] w : s) {
			int[] curr = w;

			vMap[curr[0]][curr[1]] += curr[2];

			if (max[curr[0]][curr[1]] < curr[2]) {
				max[curr[0]][curr[1]] = curr[2];
				tmpDir[curr[0]][curr[1]] = curr[3];

			}

		}
		
		// 반환 해줄 move 객체
		ArrayList<int[]> move = new ArrayList<>();

		
		for(int r = 0; r<N; r++) {
			for(int c=0; c<N; c++) {
				
				if (tmpDir[r][c] != 0) {
					map[r][c] = vMap[r][c];
					move.add(new int[] {r, c, map[r][c], tmpDir[r][c]});
				}
				
			}
		}

		return move;

	}

	private static int redir(int dir) {
		if (dir == 1)
			return 2;
		else if (dir == 2)
			return 1;
		else if (dir == 3)
			return 4;
		return 3;
	}

}