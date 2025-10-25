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
			int[] rank = new int[N + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				rank[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}

			int count = 1;
			int min = rank[1];
			for (int i = 2; i <= N; i++) {
				if (min > rank[i]) {
					min = rank[i];
					count++;
				}
			}

			sb.append(count).append("\n");
		}
		System.out.println(sb.toString());
	}
}
