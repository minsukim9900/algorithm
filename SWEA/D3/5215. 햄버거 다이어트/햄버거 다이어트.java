import java.io.*;
import java.util.*;

public class Solution {
	private static int N, L, answer;
	private static int[][] infos;
	private static int[] total_flavor;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			answer = 0;
			infos = new int[N + 1][2];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int flavor = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				infos[i][0] = flavor;
				infos[i][1] = cal;
			}

			for (int i = 1; i <= N; i++) {
				combi(1, 0, 0, 0, i);
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void combi(int idx, int depth, int flavorSum, int calSum, int range) {
		if (calSum > L ) {
			return;
		}

		if (depth == range && calSum <= L && answer < flavorSum) {
			answer = flavorSum;
		} else {
			for (int i = idx; i <= N; i++) {
				combi(i + 1, depth + 1, flavorSum + infos[i][0], calSum + infos[i][1], range);
			}
		}
	}
}