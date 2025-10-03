import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		long[] fac = new long[21];
		fac[0] = 1;
		for (int i = 1; i <= 20; i++) {
			fac[i] = fac[i - 1] * i;
		}

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());

		if (num == 1) {
			boolean[] visited = new boolean[N + 1];
			long k = Long.parseLong(st.nextToken());

			int[] ans = new int[N];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[j])
						continue;

					if (k - fac[N - i] > 0) {
						k -= fac[N - i];
					} else {
						visited[j] = true;
						ans[i - 1] = j;
						break;
					}
				}
			}

			for (int w : ans) {
				sb.append(w).append(" ");
			}
			System.out.println(sb.toString());
			return;
		} else {
			long used = 0L;
			long answer = 0L;
			for (int i = 1; i <= N; i++) {
				int x = Integer.parseInt(st.nextToken());

				long full_mask = (1 << x) - 1;

				long temp = used & full_mask;
				int cnt = x - Long.bitCount(temp) - 1;

				answer += cnt * fac[N - i];

				used |= (1 << x);
			}
			System.out.println(answer + 1);
			return;
		}
	}
}
