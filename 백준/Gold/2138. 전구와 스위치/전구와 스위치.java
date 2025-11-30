import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[][] states = new int[2][N];
		int[] statesB = new int[N];

		for (int i = 0; i < 2; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				states[i][j] = str.charAt(j) - '0';

				if (i == 0) {
					statesB[j] = states[i][j];

					if (j >= 0 && j < 2) {
						statesB[j] = states[i][j] ^ 1;
					}
				}
			}
		}
		int answer = INF;
		answer = Math.min(answer, cal(states[0], states[1], 0));
		answer = Math.min(answer, cal(statesB, states[1], 1));
		System.out.println(answer == INF ? -1 : answer);
	}

	private static int cal(int[] arr, int[] comp, int count) {

		for (int i = 1; i < N; i++) {
			if (arr[i - 1] != comp[i - 1]) {
				arr[i - 1] ^= 1;
				arr[i] ^= 1;
				if (i + 1 < N) {
					arr[i + 1] ^= 1;
				}
				count++;
			}
		}
		return check(arr, comp) ? count : INF;
	}

	private static boolean check(int[] arr, int[] comp) {
		for (int i = 0; i < N; i++) {
			if (arr[i] != comp[i])
				return false;
		}
		return true;
	}
}