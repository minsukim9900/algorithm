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
				num ^= (1<< x);
				num ^= (1<< y);
				arr.add(num);
			}
			
			int answer = 0;
			for (int i = 0; i < (1 << N); i++) {
				boolean isPoss = true;

				for (int j = 0; j < arr.size(); j++) {
					if ((i & (arr.get(j))) == arr.get(j)) {
						isPoss = false;
						break;
					}
				}

				if (isPoss) {
					answer++;
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}