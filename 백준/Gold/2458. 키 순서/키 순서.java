import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] arr;
	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];

		for (int[] w : arr) {
			Arrays.fill(w, INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			arr[from][to] = 1;
		}

		for (int k = 1; k <= N; k++) {

			for (int i = 1; i <= N; i++) {
				if (i == k)
					continue;

				for (int j = 1; j <= N; j++) {
					if (j == k || j == i)
						continue;

					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}

		}

		for (int r = 1; r <= N; r++) {
			int cnt = 0;
			for (int c = 1; c <= N; c++) {

				if (arr[r][c] >= INF)
					arr[r][c] = 0;
				if (arr[r][c] > 0)
					cnt++;
			}

			arr[r][0] = cnt;
		}

		for (int c = 1; c <= N; c++) {
			int cnt = 0;
			for (int r = 1; r <= N; r++) {
				
				if(arr[r][c] > 0) cnt++;
			}
			
			arr[0][c] = cnt;
		}
		
		int result = 0;
		for(int i = 1; i<= N; i++) {
			if(arr[i][0] + arr[0][i] == N -1) result++;
		}
		
		System.out.println(result);

	}
}
