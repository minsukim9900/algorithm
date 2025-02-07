import java.io.*;
import java.util.*;

public class Main {

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int N;
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		ArrayList<int[]> room = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			String state = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = state.charAt(c) - '0';

				if (map[r][c] == 1) {
					room.add(new int[] { r, c });
				}

			}
		}
		
		ArrayList<Integer> apt = new ArrayList<>();

		for (int i = 0; i < room.size(); i++) {
			int r = room.get(i)[0];
			int c = room.get(i)[1];

			if (!visited[r][c]) {
				apt.add(bfs(r, c));
			}

		}
		
		System.out.println(apt.size());
		Collections.sort(apt);
		for(int a : apt) {
			System.out.println(a);
		}
		
	}

	private static int bfs(int r, int c) {

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					cnt++;
					q.add(new int[] { nr, nc });
				}
			}

		}
		
		return cnt;

	}

}
