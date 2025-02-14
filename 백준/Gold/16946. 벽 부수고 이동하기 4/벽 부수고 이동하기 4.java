import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static ArrayList<int[]> changeWall = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		ArrayList<int[]> room = new ArrayList<>();
		ArrayList<int[]> wall = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			String num = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = num.charAt(c) - '0';

				if (map[r][c] == 0) {
					room.add(new int[] { r, c });
				} else {
					wall.add(new int[] { r, c });
				}

			}
		}
		ArrayList<Integer> roomCnt = new ArrayList<>();
		int idx = 2;
		for (int[] r : room) {
			if (map[r[0]][r[1]] == 0)
				roomCnt.add(bfs(r[0], r[1], idx++));
		}

		for (int[] w : wall) {
			search(w[0], w[1], roomCnt);
		}

		for (int[] w : changeWall) {
			map[w[0]][w[1]] = (w[2] % 10);
		}

		for (int[] r : room) {
			map[r[0]][r[1]] = 0;
		}

		for (int[] r : map) {
			for (int c : r) {
				sb.append(c);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}

	private static void search(int r, int c, ArrayList<Integer> roomCnt) {
		boolean[] visited = new boolean[roomCnt.size()];
		int sum = 1;
		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] >= 2 && !visited[map[nr][nc] - 2]) {
				visited[map[nr][nc] - 2] = true;
				sum += roomCnt.get(map[nr][nc] - 2);
			}

		}

		changeWall.add(new int[] { r, c, sum });

	}

	private static int bfs(int r, int c, int idx) {
		int cnt = 1;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		map[r][c] = idx;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
					map[nr][nc] = idx;
					cnt++;
					q.offer(new int[] { nr, nc });
				}
			}
		}

		return cnt;
	}

}