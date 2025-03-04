import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] dp;
	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;


		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][N + 1];

		for (int[] d : dp) {
			Arrays.fill(d, INF);
		}

		while (true) {

			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (x == -1 && y == -1)
				break;
			dp[x][y] = 1;
			dp[y][x] = 1;

		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				
				if(i == k) continue;
				
				for (int j = 1; j <= N; j++) {
					if(j == i || j == k) continue;
					
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);

				}
			}
		}
		
		int[] scores = new int[N+1];
		
		int min = INF;
		for(int r= 1; r<=N; r++) {
			
			int max = 0;
			for(int c= 1; c<=N; c++) {
				if(dp[r][c] == INF) dp[r][c] = 0;
				max = Math.max(max, dp[r][c]);
			}
			scores[r] = max;
			min = Math.min(scores[r], min);
		}
		
		int cnt = 0;
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i<=N; i++) {
			if(min == scores[i]) {
				cnt++;
				nums.add(i);
			}
		}
		
		sb.append(min + " " + cnt + "\n");
		
		for(int w : nums) {
			sb.append(w + " ");
		}
		
		System.out.println(sb.toString());

	}
}
