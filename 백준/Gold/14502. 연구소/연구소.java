import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] lab;
	private static Queue<int[]> virus = new ArrayDeque<>();
	private static int[][] realviruse;
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
		
		
		int size = virus.size();
		realviruse = new int[size][2];
		
		for(int x = 0; x<size; x++) {
			int[] tmp = virus.poll();
			realviruse[x] = tmp;
		}

		bruteforce(0);
		System.out.println(max);

	}

	private static void bruteforce(int depth) {

		if (depth == 3) {

			infectViruse();

		} else {
			for (int r = 0; r < N; r++) {

				for (int c = 0; c < M; c++) {

					if (lab[r][c] == 0) {
						
						lab[r][c] = 1;
						bruteforce(depth + 1);
						lab[r][c] = 0;
					}

				}
			}
		}

	}

	private static void infectViruse() {
		
		int[][] cloneLab = new int[N][M];

		for (int i = 0; i < N; i++) {
			cloneLab[i] = lab[i].clone();
		}

		Queue<int[]> copyVirus = new ArrayDeque<>();
		
		for(int r = 0; r<realviruse.length; r++) {
			int[] tmp = realviruse[r];
			copyVirus.add(tmp);
		}
		
		
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
		
		compareMax(cloneLab);
	}
	
	private static void compareMax(int[][] cloneLab) {
		int cnt = 0;

		for(int r = 0; r<N; r++) {
			
			for(int c = 0; c<M; c++) {
				
				if(cloneLab[r][c] == 0) {
					cnt++;
				}
				
			}
			
		}
		max = Math.max(max, cnt);
	}

}
