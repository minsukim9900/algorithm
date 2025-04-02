import java.io.*;
import java.util.*;

public class Main {
	private static int N, total, ans;
	private static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		ans = 987654321;
		total = 0;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				arr[0][j] += arr[i][j];
				arr[i][0] += arr[i][j];
				total += arr[i][j];
			}
		}

		combi(1, 0, total);
		System.out.println(ans);
	}

	private static void combi(int idx, int cnt, int sum) {

		if (idx == N + 1 || cnt == (N >> 1)) {
			ans = Math.min(ans, Math.abs(sum));

		} else {
			combi(idx + 1, cnt + 1, sum - (arr[0][idx] + arr[idx][0]));
			combi(idx + 1, cnt, sum);
		}

	}
}