import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, R;
	private static int[] info;
	private static int[][] dp;

	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][N + 1];

		info = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			info[i] = Integer.parseInt(st.nextToken());
		}

		for (int[] d : dp) {
			Arrays.fill(d, INF);
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			dp[x][y] = dp[y][x] = w;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {

				if (i == k)
					continue;
				for (int j = 1; j <= N; j++) {
					if (j == i)
						continue;
					
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);

				}
			}
		}
		
		int max = 0;
		for(int r= 1; r<=N; r++) {
			
			int sum = info[r];
			
			for(int c=1; c<=N;c++) {
				if(dp[r][c] > M) dp[r][c] = 0;
				
				if(dp[r][c] > 0) {
					sum += info[c];
				}
				
			}
			
			max = Math.max(max, sum);
		}
		
		System.out.println(max);

	}
}
