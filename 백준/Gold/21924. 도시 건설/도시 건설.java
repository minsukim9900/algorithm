import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<int[]> edges;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 부모 값 초기 셋팅
		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		long sum = 0L;
		edges = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			sum += w;
			edges.add(new int[] { x, y, w });
		}

		// 간선의 가중치 기준으로 오름차순 정렬
		Collections.sort(edges, (a, b) -> a[2] - b[2]);

		int pick = 0;
		int idx = 0;
		while (idx < M) {
			int px = findP(edges.get(idx)[0]);
			int py = findP(edges.get(idx)[1]);

			if (px != py) {
				sum -= edges.get(idx)[2];
				union(px, py);
				pick++;
			}
			idx++;
		}
		
		if(!isCheck()) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
		}
	}

	private static boolean isCheck() {
		for (int i = 1; i <= N; i++) {
			p[i] = findP(i);
		}
		int tmp = p[1];
		for (int i = 2; i <= N; i++) {
			if (tmp != p[i]) {
				return false;
			}
		}
		return true;
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