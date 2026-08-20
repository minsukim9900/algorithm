import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int totalTestCase, N;
	private static int[][] infos;

	private static int simulate() {
		Arrays.sort(infos, (a, b) -> Integer.compare(b[1], a[1]));

		int result = infos[0][1];

		for (int i = 0; i < N; i++) {
			result = Math.min(result, infos[i][1]) - infos[i][0];
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		totalTestCase = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase < totalTestCase + 1; testCase++) {
			N = Integer.parseInt(br.readLine());

			infos = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int d = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());

				infos[i][0] = d;
				infos[i][1] = t;
			}

			sb.append(simulate()).append("\n");
		}

		System.out.println(sb.toString());
	}
}
