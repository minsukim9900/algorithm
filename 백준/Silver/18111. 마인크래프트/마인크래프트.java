import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, B;
	private static int[][] land;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		land = new int[N][M];

		int min = 257;
		int max = -1;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp > max) {
					max = tmp;
				}
				if (tmp < min) {
					min = tmp;
				}
				
				land[r][c] = tmp;
			}
		}

		int time = Integer.MAX_VALUE;
		int high = 0;

		for (int i = min; i <= max; i++) {
			int curr = even(i);
			if (curr == -1) {
				continue;
			} else {
				if (time > curr) {
					time = curr;
					high = i;
				} else if (time == curr) {
					high = i;
				}
			}
		}

		System.out.println(time + " " + high);

	}

	private static int even(int high) {

		int[][] copy = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = land[r][c];
			}
		}

		int inventory = B;
		int time = 0;

		for (int r = 0; r < N; r++) {

			for (int c = 0; c < M; c++) {
				if (copy[r][c] > high) {
					int dif = copy[r][c] - high;
					inventory += dif;
					copy[r][c] -= dif;
					time += (dif * 2);
				} else if (copy[r][c] < high) {
					int dif = high - copy[r][c];
					inventory -= dif;
					copy[r][c] += dif;
					time += dif;
				}
			}

		}
		
		
		if (inventory >= 0) {
			return time;
		}
		return -1;

	}

}