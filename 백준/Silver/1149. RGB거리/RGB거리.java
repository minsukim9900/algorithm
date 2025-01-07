import java.io.*;
import java.util.*;

public class Main {

	private static int N;

	private static int[][] house;
	private static int[][] Dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		house = new int[N][3];
		Dp = new int[N][3];

		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());

			house[i][0] = Integer.parseInt(st.nextToken());
			house[i][1] = Integer.parseInt(st.nextToken());
			house[i][2] = Integer.parseInt(st.nextToken());

		}
		
		Dp[0][0] = house[0][0];
		Dp[0][1] = house[0][1];
		Dp[0][2] = house[0][2];

		for (int i = 1; i < N; i++) {
			Dp[i][0] = Math.min(Dp[i-1][1], Dp[i-1][2]) + house[i][0];
			Dp[i][1] = Math.min(Dp[i-1][0], Dp[i-1][2]) + house[i][1];
			Dp[i][2] = Math.min(Dp[i-1][1], Dp[i-1][0]) + house[i][2];
		}
		
		System.out.println(Math.min(Math.min(Dp[N-1][0], Dp[N-1][1]), Dp[N-1][2]));

	}

}
