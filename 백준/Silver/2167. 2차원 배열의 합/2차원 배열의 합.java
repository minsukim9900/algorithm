import java.io.*;
import java.util.*;

public class Main {

	public static void calculator(int[][] nums, int i, int j, int x, int y) {
		int sum = 0;
		for (int r = i; r < x; r++) {
			for (int c = j; c < y; c++) {
				sum += nums[r][c]-10000;
			}
		}
		System.out.println(sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] nums = new int[N][M];


		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				nums[r][c] = Integer.parseInt(st.nextToken()) + 10000;
			}
		}

		int K = Integer.parseInt(br.readLine());
		for (int t = 0; t < K; t++) {
			st = new StringTokenizer(br.readLine());

			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			calculator(nums, i, j, x, y);
		}
	}
}