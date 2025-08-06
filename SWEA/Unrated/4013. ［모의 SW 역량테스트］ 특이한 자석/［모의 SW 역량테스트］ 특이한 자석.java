import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, K;
	private static int[][] tobni;
	private static int[] arrow;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = 4;
			M = 8;
			K = Integer.parseInt(br.readLine());
			tobni = new int[N][M];
			arrow = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					tobni[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken()) * -1;
				simulate(idx, dir);
			}

			int num = 1;
			int answer = 0;

			for (int i = 0; i < N; i++) {
				int tmp = tobni[i][arrow[i]] == 0 ? 0 : num;
				answer += tmp;
				num *= 2;
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void simulate(int idx, int dir) {
		int[] result = turn(idx, dir);

		for (int i = 0; i < result.length; i++) {
			arrow[i] = (arrow[i] + result[i] + 8) % 8;
		}
	}

	private static int[] turn(int idx, int dir) {
		int[] result = new int[N];
		result[idx] = dir;
		// 왼쪽 탐색
		int tmp = dir;
		for (int i = idx; i > 0; i--) {
			if (tobni[i][(arrow[i] + 6) % 8] != tobni[i - 1][(arrow[i - 1] + 2) % 8]) {
				tmp *= -1;
				result[i - 1] = tmp;
			} else {
				break;
			}
		}

		// 오른쪽 탐색
		tmp = dir;
		for (int i = idx; i < N - 1; i++) {
			if (tobni[i][(arrow[i] + 2) % 8] != tobni[i + 1][(arrow[i + 1] + 6) % 8]) {
				tmp *= -1;
				result[i + 1] = tmp;
			} else {
				break;
			}
		}

		return result;
	}
}