import java.io.*;
import java.util.*;

public class Solution {

	private static int[] cost, month;
	private static int total, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			min = 987654321;
			total = 0;
			cost = new int[4];
			month = new int[13];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			
			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
				total += month[i];
			}
			
			
			dfs(1, 0);
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int depth, int sum) {

		if (depth >= 13) {
			min = Math.min(min, sum);
			return;
		}

		// 1일권 구매
		sum += (cost[0] * month[depth]);
		total -= month[depth];
		dfs(depth + 1, sum);
		sum -= (cost[0] * month[depth]);
		total += month[depth];

		// 1달권 구매
		sum += cost[1];
		total -= month[depth];
		dfs(depth + 1, sum);
		sum -= cost[1];
		total += month[depth];

		// 3달권 구매
		sum += cost[2];
		for (int i = 0; i < 3; i++) {
			if(depth + i > 12) continue;
			total -= month[depth + i];
		}
		dfs(depth + 3, sum);
		sum -= cost[2];
		for (int i = 0; i < 3; i++) {
			if(depth + i > 12) continue;
			total += month[depth + i];
		}

		// 1년권 구매
		sum += cost[3];
		for (int i = 0; i < 12; i++) {
			if(depth + i > 12) continue;
			total -= month[depth + i];
		}
		dfs(depth + 12, sum);
		sum -= cost[3];
		for (int i = 0; i < 12; i++) {
			if(depth + i > 12) continue;
			total += month[depth + i];
		}

	}
}