import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map, roomNum, roomVisited;
	private static boolean[][] visited;
	private static int[][] delta = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		roomNum = new int[M][N];
		visited = new boolean[M][N];

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<Integer> room = new ArrayList<>();
		int idx = 0;
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {

					room.add(bfs(r, c, idx++));
				}
			}
		}
		
		int result = breakWall(room);
		Collections.sort(room);
		System.out.println(room.size());
		System.out.println(room.get(room.size() - 1));
		System.out.println(result);

	}

	private static int breakWall(ArrayList<Integer> room) {

		int max = 0;

		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N - 1; c++) {
				if (roomNum[r][c] != roomNum[r][c + 1]) {

					max = Math.max(max, room.get(roomNum[r][c]) + room.get(roomNum[r][c + 1]));
				}
			}
		}

		for (int c = 0; c < N; c++) {
			for (int r = 0; r < M - 1; r++) {
				if (roomNum[r][c] != roomNum[r + 1][c]) {
					max = Math.max(max, room.get(roomNum[r][c]) + room.get(roomNum[r + 1][c]));
				}
			}
		}

		return max;

	}

	private static int bfs(int r, int c, int idx) {

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });

		int cnt = 1;
		visited[r][c] = true;
		roomNum[r][c] = idx;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int i = 0; i < 4; i++) {
				if ((map[curr[0]][curr[1]] & (1 << i)) == 0) {

					int nr = curr[0] + delta[i][0];
					int nc = curr[1] + delta[i][1];

					if (nr >= 0 && nr < M && nc >= 0 && nc < N && !visited[nr][nc]) {
						visited[nr][nc] = true;
						roomNum[nr][nc] = idx;
						q.offer(new int[] { nr, nc });
						cnt++;
					}

				}
			}

		}

		return cnt;
	}

}
