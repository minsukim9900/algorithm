import java.io.*;
import java.util.*;

public class Solution {

	private static int[][] tobni;
	private static int[] currIdx;
	private static int M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			M = 4;
			tobni = new int[M][8];
			currIdx = new int[M];

			int K = Integer.parseInt(br.readLine());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < 8; j++) {
					tobni[i][j] = Integer.parseInt(st.nextToken());
				}

			}

			for (int i = 0; i < K; i++) {

				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				circulate(idx - 1, dir);

			}

			int i = 0;
			int sum = 0;
			int x = 1;
			for(int idx : currIdx) {
				sum += (tobni[i++][idx] * x);
				x *= 2;
			}
			
			sb.append("#" + t + " " + sum + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void circulate(int idx, int dir) {

		int left = tobni[idx][(6 + currIdx[idx]) % 8];
		int right = tobni[idx][(2 + currIdx[idx]) % 8];

		int tmpDir = dir;

		for (int i = idx - 1; i >= 0; i--) {

			if (tobni[i][(2 + currIdx[i]) % 8] == left) {
				break;
			}
			left = tobni[i][(6 + currIdx[i]) % 8];
			tmpDir *= -1;
			currIdx[i] = (currIdx[i] - tmpDir + 8) % 8;

		}

		tmpDir = dir;

		for (int i = idx + 1; i < M; i++) {

			if (tobni[i][(6 + currIdx[i]) % 8] == right) {
				break;
			}

			right = tobni[i][(2 + currIdx[i]) % 8];
			tmpDir *= -1;
			currIdx[i] = (currIdx[i] - tmpDir + 8) % 8;

		}

		currIdx[idx] = (currIdx[idx] - dir + 8) % 8;

	}
}
