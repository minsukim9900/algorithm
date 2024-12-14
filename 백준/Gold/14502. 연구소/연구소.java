import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] lab;
	private static Queue<int[]> virus = new ArrayDeque<>();
	private static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][M];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());

			for (int m = 0; m < M; m++) {
				int state = Integer.parseInt(st.nextToken());

				if (state == 2) {
					virus.add(new int[] { n, m });
				}
				lab[n][m] = state;
			}
		}

		dfs(0);
		System.out.println(max);

	}

	private static void dfs(int depth) {

		if (depth == 3) {
			int[][] cloneLab = new int[N][M];

			for (int i = 0; i < N; i++) {
				cloneLab[i] = lab[i].clone();
			}

			Queue<int[]> copyVirus = new ArrayDeque<>();
			
			int size = virus.size();
			for (int i = 0; i < size; i++) {
				int[] tmp = virus.poll();
				copyVirus.add(new int[] {tmp[0], tmp[1]});
				virus.add(tmp);
			}

			bfs(cloneLab, copyVirus);
			int cnt = 0;

			for(int r = 0; r<N; r++) {
				
				for(int c = 0; c<M; c++) {
					if(cloneLab[r][c] == 0) {
						cnt++;
					}
				}
				
			}
			max = Math.max(max, cnt);

		} else {
			for (int r = 0; r < N; r++) {

				for (int c = 0; c < M; c++) {

					if (lab[r][c] == 1 || lab[r][c] == 2) {
						continue;
					}
					lab[r][c] = 1;
					dfs(depth + 1);
					lab[r][c] = 0;

				}
			}
		}

	}

	private static void bfs(int[][] cloneLab, Queue<int[]> copyVirus) {
		while (!copyVirus.isEmpty()) {
			int[] curr = copyVirus.poll();

			for (int i = 0; i < 4; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (cloneLab[nx][ny] == 0) {
						cloneLab[nx][ny] = 2;
						copyVirus.add(new int[] { nx, ny });
					}
				}
			}

		}
	}

}
