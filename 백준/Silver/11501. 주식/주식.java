import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			int[] price = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			int max = price[N - 1];

			long answer = 0;
			for (int i = N - 2; i >= 0; i--) {
				if (max < price[i]) {
					max = price[i];
				} else {
					answer += (max - price[i]);
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}
