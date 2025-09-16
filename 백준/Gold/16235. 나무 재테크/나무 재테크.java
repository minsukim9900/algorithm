import java.io.*;
import java.util.*;;

public class Main {
	private static int N, M, K;
	private static int[][] A, board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
			{ 1, 1 } };
	private static PriorityQueue<int[]>[][] land;
	private static Queue<int[]> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			Arrays.fill(board[r], 5);
		}

		land = new PriorityQueue[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				land[r][c] = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			land[r][c].add(new int[] { 0, age });
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int year = 0;
		while (K-- > 0) {
			springAndSummer(year);
			fallAndWinter(year++);
		}
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				answer += land[r][c].size();
			}
		}
		return answer;
	}

	private static void springAndSummer(int year) {
		int[][] plus = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {

				while (!land[r][c].isEmpty()) {
					if (land[r][c].peek()[0] > year)
						break;

					int[] curr = land[r][c].poll();
					if (curr[1] <= board[r][c]) {
						board[r][c] -= curr[1];
						land[r][c].add(new int[] { year + 1, curr[1] + 1 });

						if ((curr[1] + 1) % 5 == 0) {
							q.add(new int[] { r, c });
						}
					} else {
						plus[r][c] += (curr[1] / 2);
					}
				}

				board[r][c] += plus[r][c];
			}
		}
	}

	private static void fallAndWinter(int year) {
		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 8; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					land[nr][nc].add(new int[] { year + 1, 1 });
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				board[r][c] += A[r][c];
			}
		}
	}
}
