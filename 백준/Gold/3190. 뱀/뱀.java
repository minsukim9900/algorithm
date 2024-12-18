import java.io.*;
import java.util.*;

public class Main {

	private static int N, K, L;
	private static int[][] board;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		board = new int[N + 2][N + 2];
		K = Integer.parseInt(br.readLine());
		

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = 1;
		}

		L = Integer.parseInt(br.readLine());
		Queue<int[]> change = new ArrayDeque<>();

		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String info = st.nextToken();
			int dir = 1;
			if (info.equals("L")) {
				dir = -1;
			}
			change.add(new int[] { time, dir });
		}

		int result = game(1, 1, change);

		System.out.println(result);

	}
	
	private static int game(int r, int c, Queue<int[]> change) {
		
		Queue<int[]> snake = new ArrayDeque<>();
		snake.add(new int[] {r, c});
		int dir = 0;
		board[r][c] = 2;
		int time = 0;
		
		while (true) {

			if (!change.isEmpty() && time == change.peek()[0]) {
				int[] curr = change.poll();
				dir = dir + curr[1];

				if (dir == 4) {
					dir = 0;
				} else if (dir == -1) {
					dir = 3;
				}

			}

			r = r + dr[dir];
			c = c + dc[dir];
			
			if (!(r >= 1 && r <= N && c >= 1 && c <= N) || board[r][c] == 2) {
				time++;
				break;
			} else {

				if (board[r][c] != 1) {
					int[] tmp = snake.poll();
					board[tmp[0]][tmp[1]] = 0;
				}
				board[r][c] = 2;
				snake.add(new int[] {r, c});
			}

			time++;

		}
		
		return time;
	}

}
