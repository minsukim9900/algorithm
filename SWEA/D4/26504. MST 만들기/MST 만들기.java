import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = N * (N - 1) / 2;

			long[] weights = new long[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(weights);

			long minCost = 0;
			long maxCost = 0;

			for (int i = 0; i < N - 1; i++) {
				minCost += weights[i];
				int index = i * (i + 1) / 2;

				maxCost += weights[index];
			}

			sb.append(minCost).append(" ").append(maxCost).append("\n");
		}

		System.out.println(sb.toString());
	}
}
