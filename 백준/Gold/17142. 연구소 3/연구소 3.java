import java.io.*;
import java.util.*;

public class Main {

	private static int minTime = 987654321;
	private static int N, M;
	private static int[][] map;
	private static ArrayList<int[]> virus = new ArrayList<>();
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == 0) cnt++;
				
				if (map[r][c] == 2) {
					virus.add(new int[] { r, c });
					map[r][c] = -1;
				}

			}
		}
		
		if(cnt == 0) {
			System.out.println(0);
			return;
		}else {
			combi(0, 0, new int[M], cnt);
		}

		if(minTime == 987654321) minTime = -1;
		System.out.println(minTime);
		

	}

	private static void combi(int idx, int depth, int[] result, int cnt) {

		if (depth == M) {
			int n = cnt;
			int[][] tmp = new int[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					tmp[r][c] = map[r][c];
				}
			}

			Queue<int[]> q = new ArrayDeque<>();
			
			for (int s : result) {
				int[] curr = virus.get(s);
				q.offer(new int[] { curr[0], curr[1], 0 });
				tmp[curr[0]][curr[1]] = -2;
			}

			int num = 0;
			
			while (!q.isEmpty()) {
				
				
				int[] curr = q.poll();
				num = Math.max(num, curr[2]);


				for (int i = 0; i < 4; i++) {
					int nr = curr[0] + delta[i][0];
					int nc = curr[1] + delta[i][1];

					if (nr >= 0 && nr < N && nc >= 0 && nc < N && (tmp[nr][nc] == 0 || tmp[nr][nc] == -1)) {
						if(tmp[nr][nc] == 0) {
							n--;
							tmp[nr][nc] = curr[2] + 1;
							q.offer(new int[] {nr, nc, tmp[nr][nc]});
						}else if(n > 0 && tmp[nr][nc] == -1) {
							tmp[nr][nc] = curr[2] + 1;
							q.offer(new int[] {nr, nc, tmp[nr][nc]});
						}
					}
					
				}

			}
			
			boolean isClear = true;
			if(n > 0) isClear = false;
			
			if(isClear) {
				minTime = Math.min(minTime, num);
			}

		} else {

			for (int i = idx; i < virus.size(); i++) {
				result[depth] = i;
				combi(i + 1, depth + 1, result, cnt);
			}

		}

	}
}
