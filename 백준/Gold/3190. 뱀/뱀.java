import java.io.*;
import java.util.*;

public class Main {

	private static int N, K, L;
	private static int[][] board;
	private static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		board = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			board[x][y] = 1;
		}

		L = Integer.parseInt(br.readLine());
		Queue<int[]> order = new ArrayDeque<>();
		
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			
			if (dir.equals("D")) {
				order.add(new int[] { time, 1 });
			} else {
				order.add(new int[] { time, -1 });
			}
			
		}
		
		
		System.out.println(game(order));
		
	}
	
	private static int game(Queue<int[]> order) {
		
		Queue<int[]> snake = new ArrayDeque<>();
		
		int r = 0;
		int c = 0;
		board[r][c] = 2;
		
		snake.add(new int[] {0,0});
		int time = 0;
		int idx = 0;
		while(true) {
			
			r+=dir[idx][0];
			c+=dir[idx][1];
			
			if(!(r >= 0 && r < N && c>= 0 && c<N) || board[r][c] == 2) {
				time++;
				return time;
			}
			if(r >= 0 && r < N && c>= 0 && c<N) {
				if(board[r][c] == 0) {
					int[] tail = snake.poll();
					int nr = tail[0];
					int nc = tail[1];
					board[nr][nc] = 0;
				}
				board[r][c] = 2;
				snake.add(new int[] {r, c});
			}
			
			time++;
			
			if(!order.isEmpty() && time == order.peek()[0]) {
				idx += order.poll()[1];
				if(idx == 4) {
					idx = 0;
				}else if(idx == -1) {
					idx = 3;
				}
			}
			
		}
	}
}
