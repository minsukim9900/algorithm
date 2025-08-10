import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			List<Integer> arr = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;

				int num = 0;
				num ^= (1 << x);
				num ^= (1 << y);
				arr.add(num);
			}

			int answer = 1;

			for (int i = 1; i <= N; i++) {
				int comb = (1 << i) - 1;
				int limit = (1 << N);

				while (comb < limit) {
					if (isPoss(comb, arr)) {
						answer++;
					}

					int u = comb & -comb;
					int v = comb + u;
					comb = v + (((v ^ comb) / u) >> 2);
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean isPoss(int mask, List<Integer> arr) {
		for (int j = 0; j < arr.size(); j++) {
			if ((mask & (arr.get(j))) == arr.get(j)) {
				return false;
			}
		}
		return true;
	}
}