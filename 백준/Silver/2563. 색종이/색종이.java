import java.io.*;
import java.util.*;

public class Main {
	private static int[][] map = new int[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			for (int j = r; j < r + 10; j++) {
				for (int k = c; k < c + 10; k++) {
					map[j][k] = 1;
				}
			}

		}
		int sum = 0;
		for (int r = 0; r < 101; r++) {
			for (int c = 0; c < 101; c++) {
				sum += map[r][c];
			}
		}
		System.out.println(sum);
	}
}