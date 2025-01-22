import java.io.*;
import java.util.*;

public class Main {

	private static int[][] board;
	private static ArrayList<int[]>[] arr;
	private static int[][] delta = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		board = new int[21][21];
		arr = new ArrayList[3];
		for (int i = 1; i < 3; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int r = 1; r <= 19; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= 19; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] == 1) {
					arr[board[r][c]].add(new int[] { r, c });
				} else if (board[r][c] == 2) {
					arr[board[r][c]].add(new int[] { r, c });

				}
			}
		}

		search();

	}

	private static void search() {

		for (int i = 1; i < 3; i++) {

			for (int idx = 0; idx < arr[i].size(); idx++) {

				int[] curr = arr[i].get(idx);
				int cnt = 1;

				for (int d = 0; d < 4; d++) {

					int nr = curr[0] + delta[d][0];
					int nc = curr[1] + delta[d][1];

					if (board[nr][nc] != i)
						continue;
					cnt++;

					while (true) {

						nr += delta[d][0];
						nc += delta[d][1];

						if (board[nr][nc] != i)
							break;

						cnt++;
					}

					int preR = curr[0] - delta[d][0];
					int preC = curr[1] - delta[d][1];

					if (board[preR][preC] != i && cnt == 5) {
						System.out.println(i);
						System.out.println(curr[0] + " " + curr[1]);
						return;
					} else {
						cnt = 1;
					}

				}

			}

		}

		System.out.println(0);
		return;
	}

}