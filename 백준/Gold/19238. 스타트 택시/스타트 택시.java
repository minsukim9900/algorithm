import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] board;
	private static int[][] passenger;
	private static int[] taxi;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[] isUsed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int state = Integer.parseInt(st.nextToken());
				board[r][c] = state == 1 ? -1 : state;
			}
		}

		taxi = new int[2];
		st = new StringTokenizer(br.readLine());
		taxi[0] = Integer.parseInt(st.nextToken());
		taxi[1] = Integer.parseInt(st.nextToken());

		isUsed = new boolean[M + 1];
		passenger = new int[M + 1][4];
		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			passenger[i][0] = sr;
			passenger[i][1] = sc;
			passenger[i][2] = er;
			passenger[i][3] = ec;
			board[sr][sc] = i;
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		for (int served = 0; served < M; served++) {
			int[] pick = findNearestPassenger(taxi[0], taxi[1]);
			if (pick == null)
				return -1;

			int idx = pick[0];
			int pr = pick[1];
			int pc = pick[2];
			int taxiAndPassengerDistance = pick[3];

			if (K < taxiAndPassengerDistance)
				return -1;

			K -= taxiAndPassengerDistance;

			int passengerDestinationDistance = bfsDistance(pr, pc, passenger[idx][2], passenger[idx][3]);

			if (passengerDestinationDistance < 0 || K < passengerDestinationDistance)
				return -1;
			
			K += (passengerDestinationDistance);
			taxi[0] = passenger[idx][2];
			taxi[1] = passenger[idx][3];
			isUsed[idx] = true;
		}
		return K;
	}

	private static int bfsDistance(int sr, int sc, int er, int ec) {
		int[][] dist = new int[N + 1][N + 1];
		for (int[] row : dist) {
			Arrays.fill(row, -1);
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc });
		dist[sr][sc] = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int d = dist[r][c];

			if (r == er && c == ec) {
				return d;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] != -1 && dist[nr][nc] == -1) {
					dist[nr][nc] = d + 1;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return -1;
	}

	private static int[] findNearestPassenger(int tr, int tc) {
		int[][] dist = new int[N + 1][N + 1];
		for (int[] row : dist) {
			Arrays.fill(row, -1);
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { tr, tc });
		dist[tr][tc] = 0;

		int bestD = Integer.MAX_VALUE;
		List<int[]> cand = new ArrayList<>();

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int d = dist[r][c];

			if (d > bestD)
				break;

			if (board[r][c] > 0 && !isUsed[board[r][c]]) {
				bestD = d;
				cand.add(new int[] { board[r][c], r, c });
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] != -1 && dist[nr][nc] == -1) {
					dist[nr][nc] = d + 1;
					q.add(new int[] { nr, nc });
				}
			}
		}

		if (cand.isEmpty())
			return null;
		cand.sort((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
		int[] pick = cand.get(0);

		return new int[] { pick[0], pick[1], pick[2], bestD };
	}

	private static boolean isRange(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}
}