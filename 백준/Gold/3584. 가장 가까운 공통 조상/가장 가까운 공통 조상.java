import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			int[] p = new int[N + 1];
			boolean[] visited = new boolean[N + 1];

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				p[to] = from;
			}

			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int cur = x;
			while (cur != 0) {
				visited[cur] = true;
				cur = p[cur];
			}
			
			cur = y;
			while(!visited[cur]) {
				cur = p[cur];
			}
			sb.append(cur).append("\n");
		}
		System.out.println(sb.toString());
	}
}