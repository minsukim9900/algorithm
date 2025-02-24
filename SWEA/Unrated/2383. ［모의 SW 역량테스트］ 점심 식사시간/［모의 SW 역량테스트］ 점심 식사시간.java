import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M, S, min;
	private static int[][] map;
	private static ArrayList<int[]> person, stair;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			min = Integer.MAX_VALUE;
			person = new ArrayList<>();
			stair = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {

					map[r][c] = Integer.parseInt(st.nextToken());

					if (map[r][c] == 1) {
						person.add(new int[] { r, c });
					} else if (map[r][c] > 1) {
						stair.add(new int[] { r, c, map[r][c] });
					}

				}

			}

			M = person.size();
			S = stair.size();
			perm(0, new int[M]);
			
			sb.append("#"+ t + " " + min + "\n");
		}
		
		System.out.println(sb.toString());

	}

	private static void perm(int depth, int[] select) {

		if (depth == M) {
			update(select);
		} else {

			for (int i = 0; i < S; i++) {
				select[depth] = i;
				perm(depth + 1, select);
			}

		}
	}

	private static void update(int[] select) {

		int[] cnt = new int[S];
		for (int i = 0; i < M; i++) {
			cnt[select[i]]++;
		}

		int[][][] info = new int[S][][];

		for (int i = 0; i < S; i++) {
			info[i] = new int[cnt[i]][3];
		}

		for (int i = 0; i < M; i++) {
			int[] p = person.get(i);
			int[] s = stair.get(select[i]);

			int d = Math.abs(p[0] - s[0]) + Math.abs(p[1] - s[1]);

			info[select[i]][--cnt[select[i]]] = new int[] { i, d, select[i], stair.get(select[i])[2], 0 };

		}

		for (int i = 0; i < S; i++) {
			Arrays.sort(info[i], new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {

					if (o1[1] == o2[1]) {
						return o1[0] - o2[0];
					}

					return o1[1] - o2[1];
				}
			});
		}

		min = Math.min(min,cal(info));
		
	}

	private static int cal(int[][][] info) {
		
		int time = 987654321;
		for (int i = 0; i < info.length; i++) {
			if (info[i].length == 0)
				continue;
			time = Math.min(time, info[i][0][1]);
		}

		int cnt = 0;

		int[] stairCnt = new int[S];

		Queue<int[]>[] wait = new ArrayDeque[S];
		for (int i = 0; i < S; i++) {
			wait[i] = new ArrayDeque<>();
		}

		Queue<int[]>[] start = new ArrayDeque[S];
		for (int i = 0; i < S; i++) {
			start[i] = new ArrayDeque<>();
		}

		while (cnt < M) {
			
			if(cnt == M) break;
			for (int i = 0; i < info.length; i++) {
				if (info[i].length == 0)
					continue;

				for (int j = 0; j < info[i].length; j++) {
					if (info[i][j][1] == time) {
						wait[i].add(info[i][j]);
					}
				}
			}
			

			for (int i = 0; i < S; i++) {
				
				while (stairCnt[i] < 3 && !wait[i].isEmpty() && wait[i].peek()[1]  < time) {
					stairCnt[i]++;
					int[] curr = wait[i].poll();
					curr[4] += time;
					start[i].add(curr);
				}
				
			}
			

			for (int i = 0; i < S; i++) {
				
				while (!start[i].isEmpty() && time >= start[i].peek()[4] + start[i].peek()[3] - 1) {
					stairCnt[i]--;
					cnt++;
					int[] curr = start[i].poll();
				}
			}
			
			
			
			time++;

		}
		
		return time;

	}

}