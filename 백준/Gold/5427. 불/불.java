import java.io.*;
import java.util.*;

public class Main {
	private static int W, H;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			board = new int[H + 2][W + 2];
			List<int[]> human = new ArrayList<>();
			List<int[]> fire = new ArrayList<>();

			for (int r = 1; r <= H; r++) {
				String str = br.readLine();
				for (int c = 1; c <= W; c++) {
					char tmp = str.charAt(c - 1);
					int num = 0;

					if (tmp == '#') {
						num = -1;
					} else if (tmp == '@') {
						num = 1;
						human.add(new int[] { r, c, num });
					} else if (tmp == '*') {
						num = -3;
						fire.add(new int[] { r, c, num });
					}
					board[r][c] = num;
				}
			}
			int answer = simulate(human, fire);
			
			if(answer == 0) {
				sb.append("IMPOSSIBLE").append("\n");
			}else {
				sb.append(answer).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static int simulate(List<int[]> human, List<int[]> fire) {
		int cnt = 0;

		Queue<int[]> currFire = new ArrayDeque<>();
		Queue<int[]> currHuman = new ArrayDeque<>();

		currFire.addAll(fire);
		currHuman.addAll(human);

		out : while (currHuman.size() > 0) {
			int tmpSize = currFire.size();

			for (int i = 0; i < tmpSize; i++) {
				int[] curr = currFire.poll();

				for (int j = 0; j < 4; j++) {
					int nr = curr[0] + delta[j][0];
					int nc = curr[1] + delta[j][1];

					if (nr >= 1 && nr <= H && nc >= 1 && nc <= W && board[nr][nc] >= 0) {
						board[nr][nc] = curr[2];
						currFire.add(new int[] { nr, nc, curr[2] });
					}
				}
			}

			int humanSize = currHuman.size();

			for (int i = 0; i < humanSize; i++) {
				int[] curr = currHuman.poll();
				if (curr[0] == 0 || curr[1] == 0 || curr[0] == H + 1 || curr[1] == W + 1) {
					cnt = curr[2] - 1;
					break out;
				}
					for (int j = 0; j < 4; j++) {
						int nr = curr[0] + delta[j][0];
						int nc = curr[1] + delta[j][1];

						if (nr >= 0 && nr <= H + 1 && nc >= 0 && nc <= W + 1 && board[nr][nc] == 0) {
							board[nr][nc] = curr[2];
							currHuman.add(new int[] { nr, nc, curr[2] + 1 });
						}
					}
			}
		}
		return cnt;
	}

}
