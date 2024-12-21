import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[][] lab;
	private static int[] result = new int[3];
	private static ArrayList<int[]> virus, empty;
	private static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];

		virus = new ArrayList<>();
		empty = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 0) {
					empty.add(new int[] { r, c });
				} else if (tmp == 2) {
					virus.add(new int[] { r, c });
				}
				lab[r][c] = tmp;
			}
		}

		comb(0, 0);
		System.out.println(max);

	}

	public static void comb(int num, int depth) {
		if (depth == 3) {

			int[][] copyLab = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copyLab[i][j] = lab[i][j];
				}
			}

			for (int i = 0; i < 3; i++) {
				int idx = result[i];
				int nr = empty.get(idx)[0];
				int nc = empty.get(idx)[1];
				copyLab[nr][nc] = 1;
			}


			spreadVirus(copyLab);

		} else {
			for (int i = num; i < empty.size(); i++) {
				result[depth] = i;
				comb(i + 1, depth + 1);
			}
		}
	}

	public static void spreadVirus(int[][] copyLab) {
		ArrayList<int[]> copyVirus = new ArrayList<>();
		for(int[] w : virus) {
			copyVirus.add(new int[] {w[0], w[1]});
		}
		
		int idx = 0;

		while (idx < copyVirus.size()) {
			int[] curr = copyVirus.get(idx);
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && copyLab[nr][nc] == 0) {
					copyLab[nr][nc] = 2;
					copyVirus.add(new int[] { nr, nc });
				}

			}

			idx++;

		}


		cntVirus(copyLab);

	}

	private static void cntVirus(int[][] copyLab) {
		int cnt = 0;

		for (int r = 0; r < N; r++) {

			for (int c = 0; c < M; c++) {

				if (copyLab[r][c] == 0) {
					cnt++;
				}

			}

		}

		max = Math.max(max, cnt);
	}

}
