import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					q.add(new int[] { i, j });
				}
			}
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int px = findP(curr[0]);
			int py = findP(curr[1]);

			if (px != py) {
				union(px, py);
			}
		}

		for (int i = 1; i <= N; i++) {
			findP(i);
		}
		
		st = new StringTokenizer(br.readLine());
		int result = p[Integer.parseInt(st.nextToken())];
		String answer = "YES";
		for(int i = 0; i < M - 1; i++) {
			if(result != p[Integer.parseInt(st.nextToken())]) {
				answer = "NO";
				break;
			}
		}
		System.out.println(answer);
	}

	private static int findP(int x) {
		if (p[x] != x) {
			p[x] = findP(p[x]);
		}
		return p[x];
	}

	private static void union(int x, int y) {
		p[y] = x;
	}
}