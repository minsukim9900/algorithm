import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] board;
	private static int[][] delta = { { 0, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 },
			{ 1, 0 }, { 1, -1 } };

	private static Queue<int[]> groom = new ArrayDeque<>();
	private static List<int[]> info = new ArrayList<>();
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N + 2][N + 2];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				board[r][c] = tmp;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			info.add(new int[] { dir, cnt });
		}
		
		start();
		System.out.println(count());
	}

	private static void start() {
		groom.offer(new int[] { N - 1, 1 });
		groom.offer(new int[] { N - 1, 2 });
		groom.offer(new int[] { N, 1 });
		groom.offer(new int[] { N, 2 });
		int m = 0;
		
		while (m != M) {

			int[] order = info.get(m);
			int dir = order[0];
			int cnt = order[1];

			visited = new boolean[N + 2][N + 2];
			int size = groom.size();
			Queue<int[]> bug = new ArrayDeque<>();

			for (int i = 0; i < size; i++) {
				int[] curr = groom.poll();
				move(bug, curr, dir, cnt);
			}
			
			
			copy(bug);
			create();
			
			m++;
		}

	}

	private static void move(Queue<int[]> copy, int[] curr, int dir, int cnt) {
		int r = curr[0];
		int c = curr[1];

		for (int i = 0; i < cnt; i++) {
			r += delta[dir][0];

			if (r == 0) {
				r = N;
			} else if (r == N + 1) {
				r = 1;
			}

			c += delta[dir][1];

			if (c == 0) {
				c = N;
			} else if (c == N + 1) {
				c = 1;
			}

		}

		copy.offer(new int[] { r, c });
		board[r][c] += 1;
		visited[r][c] = true;

	}

	private static void copy(Queue<int[]> copy) {

		while (!copy.isEmpty()) {

			int[] curr = copy.poll();
			int r = curr[0];
			int c = curr[1];
			for (int i = 2; i <= 8; i += 2) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
					if (board[nr][nc] > 0) {
						board[r][c] += 1;
					}
				}
			}
		}

	}

	private static void create() {
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(board[r][c] >= 2 && !visited[r][c]) {
					board[r][c] -= 2;
					groom.offer(new int[] { r, c});
				}
			}
		}
		
	}
	
	private static int count() {
		int sum = 0;
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				sum += board[r][c];
			}
		}
		
		return sum;
		
	}
}