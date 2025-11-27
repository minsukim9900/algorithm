import java.io.*;
import java.util.*;

public class Main {
	private static int N, X;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		int[][] info = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = info[i][0] - info[i][1];
		}

		int minPrice = 1000 * N;

		Arrays.sort(info, (a, b) -> b[2] - a[2]);
		long answer = 0L;
		for (int i = 0; i < N; i++) {
			minPrice -= 1000;
			
			if (info[i][2] > 0 && X - 5000 >= minPrice) {
				X -= 5000;
				answer += info[i][0];
			} else {
				X -= 1000;
				answer += info[i][1];
			}
		}
		System.out.println(answer);
	}
}