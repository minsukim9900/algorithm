import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] board, ans;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = N * N;
		board = new int[N][N];
		ans = new int[N][N];

		adj = new int[M + 1][M + 1];

		ArrayList<Integer> order = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			order.add(from);
			for (int j = 0; j < 4; j++) {
				int to = Integer.parseInt(st.nextToken());
				adj[from][to] = 1;
			}
		}
		
		simulate(order);
		
		int rs = 0;
		
		for(int r = 0; r<N; r++) {
			
			for(int c = 0; c<N; c++) {
				int cnt = cntLikeNum(ans[r][c], r, c);
				rs += satisfaction(cnt);
			}
		}
		System.out.println(rs);
	}

	private static int satisfaction(int cnt) {
		if(cnt == 0) return 0;
		if(cnt == 1) return 1;
		if(cnt == 2) return 10;
		if(cnt == 3) return 100;
		 return 1000;
	}
	

	private static void simulate(ArrayList<Integer> order) {
		initBoard();
		
		for (int i = 0; i < order.size(); i++) {
			int idx = order.get(i);
			int[] info = findSeat(idx);
			ans[info[0]][info[1]] = idx;
			minusSpace(info[0], info[1]);
		}
	}

	private static int[] findSeat(int idx) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[3] == o2[3]) {

					if (o1[2] == o2[2]) {
						if (o1[0] == o2[0]) {
							return o1[1] - o2[1];
						}

						return o1[0] - o2[0];
					}

					return o2[2] - o1[2];
				}

				return o2[3] - o1[3];
			}
		});

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (ans[r][c] != 0)
					continue;
				int likeCnt = cntLikeNum(idx, r, c);

				pq.add(new int[] { r, c, board[r][c], likeCnt });
			}
		}
		
		return pq.poll();
	}

	private static void initBoard() {

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				board[r][c] = cntSpace(r, c);
			}
		}

	}
	
	private static void minusSpace(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc) && ans[nr][nc] == 0) {
				board[nr][nc]--;
			}
		}
	}

	private static int cntSpace(int r, int c) {
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc) && ans[nr][nc] == 0) {
				cnt++;
			}
		}
		return cnt;
	}

	private static int cntLikeNum(int idx, int r, int c) {
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc) && adj[idx][ans[nr][nc]] == 1) {
				cnt++;
			}
		}
		return cnt;
	}

	private static boolean isRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
}