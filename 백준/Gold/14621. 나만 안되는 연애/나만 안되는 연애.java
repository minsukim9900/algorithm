
import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] infos = new int[N + 1];
		p = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			infos[i] = st.nextToken().equals("M") ? 1 : 2;
			p[i] = i;
		}

		List<int[]> edges = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if (infos[from] == infos[to])
				continue;

			edges.add(new int[] { from, to, weight });
		}

		edges.sort((a, b) -> Integer.compare(a[2], b[2]));

		int pick = 0;
		int answer = 0;

		for (int i = 0; i < edges.size(); i++) {
			int[] curr = edges.get(i);

			int x = curr[0];
			int y = curr[1];
			int w = curr[2];
			int px = findP(x);
			int py = findP(y);

			if (px != py) {
				pick++;
				union(px, py);

				answer += w;
			}
		}

		System.out.println(pick == N - 1 ? answer : -1);
	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}

		return p[x];
	}

	private static void union(int x, int y) {
		p[y] = x;
	}
}