import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	private static int N, L, R;
	private static int[][] board;
	private static boolean[][] visited;
	private static Queue<int[]> q;
	private static List<int[]> list;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		while (true) {
			boolean check = false;
			visited = new boolean[N][N];

			for (int r = 0; r < N; r++) {

				for (int c = 0; c < N; c++) {

					if (!visited[r][c]) {
						int sum = bfs(r, c);
						if(list.size() > 1) {
							check = true;
							changeBoard(sum);
						}
					}
				}
			}
			if (!check) {
				break;
			}
			
			day++;

		}
		System.out.println(day);
	}

	private static int bfs(int r, int c) {
		q = new ArrayDeque<>();
		list = new ArrayList<>();
		q.offer(new int[] { r, c });
		list.add(new int[] { r, c });
		int sum = board[r][c];
		visited[r][c] = true;
		while (!q.isEmpty()) {
			
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					if (Math.abs(board[curr[0]][curr[1]] - board[nr][nc]) >= L
							&& Math.abs(board[curr[0]][curr[1]] - board[nr][nc]) <= R) {
						visited[nr][nc] = true;
						sum += board[nr][nc];
						q.offer(new int[] { nr, nc });
						list.add(new int[] { nr, nc });
					}
				}
			}

		}

		return sum;
	}

	private static void changeBoard(int sum) {
		int tmp = sum / list.size();

		for (int[] w : list) {
			board[w[0]][w[1]] = tmp;
		}

	}

}