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
			int[][] info = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				info[i][0] = Integer.parseInt(st.nextToken());
				info[i][1] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(info, (a, b) -> Integer.compare(a[0], b[0]));
			int count = 1;
			int min = info[0][1];

			for (int i = 1; i < N; i++) {
				if (min > info[i][1]) {
					min = info[i][1];
					count++;
				}
			}

			sb.append(count).append("\n");
		}
		System.out.println(sb.toString());
	}
}
