import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M, K, A, B;
	private static int[] at, bt;
	private static Queue<int[]> pt;
	private static ArrayList<Integer>[] survey;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			at = new int[N + 1];
			bt = new int[M + 1];
			pt = new ArrayDeque<>();

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= N; i++) {
				at[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= M; i++) {
				bt[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= K; i++) {
				pt.add(new int[] { i, Integer.parseInt(st.nextToken()) });
			}
			
			aftertime();
			
			int sum = 0;
			
			for(int i = 1; i<=K; i++) {
				
				if(survey[i].get(0) == A && survey[i].get(1) == B) {
					sum += i;
				}
				
			}
			
			if(sum == 0) sum = -1;
			sb.append("#" + t + " " + sum +"\n");
		}
		System.out.println(sb.toString());
	}

	private static void aftertime() {

		survey = new ArrayList[K + 1];

		for (int i = 1; i <= K; i++) {
			survey[i] = new ArrayList<>();
		}

		Queue<int[]>[] aq = new ArrayDeque[N + 1];

		for (int i = 1; i <= N; i++) {
			aq[i] = new ArrayDeque<>();
		}

		Queue<int[]>[] bq = new ArrayDeque[M + 1];

		for (int i = 1; i <= M; i++) {
			bq[i] = new ArrayDeque<>();
		}

		Queue<Integer> wait = new ArrayDeque<>();

		int t = 0;
		int pick = 0;
		while (true) {
			
			// 접수 창구
			for (int i = 1; i <= N; i++) {

				if (!aq[i].isEmpty() && aq[i].peek()[1] == t) {
					int num = aq[i].poll()[0];
					survey[num].add(i);
					wait.add(num);
				}

				if (!pt.isEmpty() && pt.peek()[1] <= t && aq[i].isEmpty()) {
					int[] curr = pt.poll();
					aq[i].add(new int[] { curr[0], t + at[i] });
				}

			}
			

			// 정비 창구

			for (int i = 1; i <= M; i++) {

				if (!bq[i].isEmpty() && bq[i].peek()[1] == t) {
					int num = bq[i].poll()[0];
					survey[num].add(i);
					pick++;
				}

				if (!wait.isEmpty() && bq[i].isEmpty()) {
					int curr = wait.poll();
					bq[i].add(new int[] { curr, t + bt[i] });
				}

			}

			if (pick == K)
				break;

			t++;
		}


	}
}
