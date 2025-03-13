import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {

				for (int i = 0; i < 5; i++) {
					max = Math.max(max, cal(r, c, i));
				}
			}
		}
		
		System.out.println(max);

	}

	private static int cal(int r, int c, int s) {

		ArrayList<int[]>[] shape = select(s);
		
		int max = 0;
		
		for (int i = 0; i < shape.length; i++) {
			
			int cnt = 0;
			ArrayList<int[]> curr = shape[i];
			
			boolean isPoss = true;
			
			for (int j = 0; j < curr.size(); j++) {
				int nr = r + curr.get(j)[0];
				int nc = c + curr.get(j)[1];
				
				if(nr < 0 || nr >= N || nc < 0 ||nc >= M) {
					isPoss = false;
					break;
				}
				
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					cnt += map[nr][nc];
				}
			}
			
			if(isPoss) {
				max = Math.max(max, cnt);
			}
		}

		return max;
	}

	private static ArrayList<int[]>[] select(int num) {

		ArrayList<int[]>[] shape = null;

		switch (num) {
		case 0: {
			shape = new ArrayList[2];
			for (int i = 0; i < 2; i++) {
				shape[i] = new ArrayList<>();
			}

			shape[0].add(new int[] { 0, 0 });
			shape[0].add(new int[] { 0, 1 });
			shape[0].add(new int[] { 0, 2 });
			shape[0].add(new int[] { 0, 3 });

			shape[1].add(new int[] { 0, 0 });
			shape[1].add(new int[] { 1, 0 });
			shape[1].add(new int[] { 2, 0 });
			shape[1].add(new int[] { 3, 0 });

			break;
		}
		case 1: {
			shape = new ArrayList[1];
			for (int i = 0; i < 1; i++) {
				shape[i] = new ArrayList<>();
			}

			shape[0].add(new int[] { 0, 0 });
			shape[0].add(new int[] { 0, 1 });
			shape[0].add(new int[] { 1, 0 });
			shape[0].add(new int[] { 1, 1 });

			break;
		}
		case 2: {
			shape = new ArrayList[8];
			for (int i = 0; i < 8; i++) {
				shape[i] = new ArrayList<>();
			}

			shape[0].add(new int[] { 0, 0 });
			shape[0].add(new int[] { 0, 1 });
			shape[0].add(new int[] { 1, 0 });
			shape[0].add(new int[] { 2, 0 });

			shape[1].add(new int[] { 0, 0 });
			shape[1].add(new int[] { 1, 0 });
			shape[1].add(new int[] { 1, 1 });
			shape[1].add(new int[] { 1, 2 });

			shape[2].add(new int[] { 0, 1 });
			shape[2].add(new int[] { 1, 1 });
			shape[2].add(new int[] { 2, 1 });
			shape[2].add(new int[] { 2, 0 });

			shape[3].add(new int[] { 0, 0 });
			shape[3].add(new int[] { 0, 1 });
			shape[3].add(new int[] { 0, 2 });
			shape[3].add(new int[] { 1, 2 });

			shape[4].add(new int[] { 0, 0 });
			shape[4].add(new int[] { 0, 1 });
			shape[4].add(new int[] { 1, 1 });
			shape[4].add(new int[] { 2, 1 });

			shape[5].add(new int[] { 0, 0 });
			shape[5].add(new int[] { 1, 0 });
			shape[5].add(new int[] { 0, 1 });
			shape[5].add(new int[] { 0, 2 });

			shape[6].add(new int[] { 0, 0 });
			shape[6].add(new int[] { 1, 0 });
			shape[6].add(new int[] { 2, 0 });
			shape[6].add(new int[] { 2, 1 });

			shape[7].add(new int[] { 1, 0 });
			shape[7].add(new int[] { 1, 1 });
			shape[7].add(new int[] { 1, 2 });
			shape[7].add(new int[] { 0, 2 });
			break;
		}
		case 3: {
			shape = new ArrayList[4];
			for (int i = 0; i < 4; i++) {
				shape[i] = new ArrayList<>();
			}

			shape[0].add(new int[] { 0, 1 });
			shape[0].add(new int[] { 1, 1 });
			shape[0].add(new int[] { 1, 0 });
			shape[0].add(new int[] { 2, 0 });

			shape[1].add(new int[] { 0, 0 });
			shape[1].add(new int[] { 0, 1 });
			shape[1].add(new int[] { 1, 1 });
			shape[1].add(new int[] { 1, 2 });

			shape[2].add(new int[] { 0, 0 });
			shape[2].add(new int[] { 1, 0 });
			shape[2].add(new int[] { 1, 1 });
			shape[2].add(new int[] { 2, 1 });

			shape[3].add(new int[] { 1, 0 });
			shape[3].add(new int[] { 1, 1 });
			shape[3].add(new int[] { 0, 1 });
			shape[3].add(new int[] { 0, 2 });

			break;
		}
		case 4: {

			shape = new ArrayList[4];
			for (int i = 0; i < 4; i++) {
				shape[i] = new ArrayList<>();
			}

			shape[0].add(new int[] { 1, 0 });
			shape[0].add(new int[] { 1, 1 });
			shape[0].add(new int[] { 0, 1 });
			shape[0].add(new int[] { 1, 2 });

			shape[1].add(new int[] { 1, 0 });
			shape[1].add(new int[] { 0, 1 });
			shape[1].add(new int[] { 1, 1 });
			shape[1].add(new int[] { 2, 1 });

			shape[2].add(new int[] { 0, 0 });
			shape[2].add(new int[] { 0, 1 });
			shape[2].add(new int[] { 1, 1 });
			shape[2].add(new int[] { 0, 2 });

			shape[3].add(new int[] { 0, 0 });
			shape[3].add(new int[] { 1, 0 });
			shape[3].add(new int[] { 1, 1 });
			shape[3].add(new int[] { 2, 0 });

			break;
		}

		}
		return shape;

	}

}
