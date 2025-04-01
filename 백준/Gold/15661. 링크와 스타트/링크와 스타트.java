import java.io.*;
import java.util.*;

public class Main {
	private static int N, ans;
	private static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		ans = 987654321;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < N; i++) {
			combi(1, 0, i, 0);
		}

		System.out.println(ans);
	}

	private static void combi(int idx, int depth, int M, int pick) {

		if (depth == M) {
			int num = divideTeam(pick);
			ans = Math.min(ans, num);
		} else {

			for (int i = idx; i <= N - M + depth; i++) {
				pick |= (1 << i);
				combi(i + 1, depth + 1, M, pick);
				pick ^= (1 << i);
			}
		}

	}

	private static int divideTeam(int pick) {
		StringBuilder sb = new StringBuilder();
		int sc1 = 0;
		int sc2 = 0;

		for (int i = 1; i <= N; i++) {
			if ((pick & (1 << i)) != 0) {
				sc1 += cal(i, 1, pick);
			} else {
				sc2 += cal(i, 0, pick);
			}
		}

		return Math.abs(sc2 - sc1);
	}

	private static int cal(int idx, int num, int pick) {
		int sum = 0;

		for (int i = 1; i <= N; i++) {
			if (i == idx)
				continue;

			if (num == 1) {
				if ((pick & (1 << i)) != 0) {
					sum += arr[idx][i];
				}
			} else {
				if ((pick & (1 << i)) == 0) {
					sum += arr[idx][i];
				}
			}
		}
		return sum;
	}

}