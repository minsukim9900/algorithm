import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<int[]> arr;
	private static int[] p;
	private static int[] size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
			size[i] = 1;
		}

		arr = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr.add(new int[] { x, y });
		}

		int answer = 0;
		for (int i = 1; i <= arr.size(); i++) {
			int[] curr = arr.get(i - 1);

			int px = findP(curr[0]);
			int py = findP(curr[1]);

			if (px != py) {
				union(px, py);
			} else {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}
		return p[x];
	}

	private static void union(int x, int y) {
		if (size[x] >= size[y]) {
			p[y] = x;
			size[x]++;
		} else {
			p[x] = y;
			size[y]++;
		}
	}

}
