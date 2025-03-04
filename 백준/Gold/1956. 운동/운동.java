import java.io.*;
import java.util.*;

public class Main {

	private static int V, E;
	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		int[][] dp = new int[V + 1][V + 1];

		for (int[] d : dp) {
			Arrays.fill(d, INF);
		}


		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			dp[from][to] = w;

		}

		for (int k = 1; k <= V; k++) {

			for (int i = 1; i <= V; i++) {

				for (int j = 1; j <= V; j++) {
						
					if (i == j || j == k)
						continue;

					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					
				}
				
				
			}
			
		}
		
		
		int min = INF;
		for (int r = 1; r <= V; r++) {
			for (int c = 1; c <= V; c++) {
				if(r == c) continue;
				if(min > dp[r][c] + dp[c][r]) {
					min = dp[r][c] + dp[c][r];
				}
			}
		}
		
		
		if(min >= INF) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}


	}
}
