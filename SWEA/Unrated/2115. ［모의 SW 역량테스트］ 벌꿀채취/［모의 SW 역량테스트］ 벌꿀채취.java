import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M, C, max, rs;
	private static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			rs = 0;
			
			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}

			}

			// 첫 번째 사람이 고르는 벌통
			for (int r = 0; r < N; r++) {

				for (int c = 0; c < N - M + 1; c++) {
					int[] honey1 = new int[M];

					for (int i = 0; i < M; i++) {
						honey1[i] = map[r][c + i];
					}

					int sum1 = select(honey1);

					// 두 번쨰 사람이 고르는 벌통
					for (int k = r; k < N; k++) {

						for (int l = 0; l < N - M + 1; l++) {
							if (k == r && (l <= c + M - 1))
								continue;

							int[] honey2 = new int[M];

							for (int j = 0; j < M; j++) {
								honey2[j] = map[k][l + j];
							}
							
							int sum2 = select(honey2);
							
							rs = Math.max(rs, sum1 + sum2);

						}

					}

				}

			}
			
			sb.append("#" + t + " " + rs + "\n");
		}
		System.out.println(sb.toString());

	}

	private static int select(int[] honey) {

		int sum = 0;

		for (int i : honey) {
			sum += i;
		}

		// 선택한 벌통이 기준치보다 크다면 조합으로 대소 비교를 해야 한다.
		if (sum > C) {

			max = 0;
			for (int i = 1; i < M; i++) {
				combi(0, 0, i, 0, 0, honey);
			}
			return max;

		} else {
			int result = 0;
			for (int w : honey) {
				result += (w * w);
			}
			
			return result;
		}

	}

	private static void combi(int idx, int depth, int end, int result, int sum, int[] honey) {
		
		if(sum > C ) return;
		
		if (depth == end) {

			max = Math.max(max, result);

		} else {

			for (int i = idx; i < honey.length; i++) {
				sum += honey[i];
				result += (honey[i] * honey[i]);
				combi(i + 1, depth + 1, end, result, sum, honey);
				result -= (honey[i] * honey[i]);
				sum -= honey[i];
			}

		}

	}

}
