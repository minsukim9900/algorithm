import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] map;
	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = dynamic();
		// 경 출 도

		for (int i = 0; i < N; i++) { // 경로
			
			
			for (int j = 0; j < N; j++) { // 출발
				
				if(i == j) continue;
				
				for (int k = 0; k < N; k++) { // 도착
					
					if(i == k) continue;
					
					
					dp[j][k] = Math.min(dp[j][i] + dp[i][k], dp[j][k]);
				}

			}

		}
		
		
		for(int r = 0; r<N; r++	) {
			for(int c = 0; c<N; c++) {
				if(dp[r][c] >= INF) dp[r][c] = 0;
				if(dp[r][c] > 0) dp[r][c] = 1;
				
				sb.append(dp[r][c] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		

	}

	private static int[][] dynamic() {
		int[][] dp = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0) {
					dp[r][c] = INF;
				} else {
					dp[r][c] = map[r][c];
				}
			}
		}

		return dp;
	}
}
