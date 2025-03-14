import java.io.*;
import java.util.*;

public class Solution {

	private static int N;

	private static int[][] delta = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());

			List<int[]> atom = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) << 1;
				int y = (Integer.parseInt(st.nextToken()) + 1000) << 1;
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());

				atom.add(new int[] { y, x, dir, k });
			}

			sb.append("#" + t + " " + simulate(atom) + "\n");

		}
		System.out.println(sb.toString());

	}

	private static int simulate(List<int[]> atom) {

		int total = 0;

		for (int t = 0; t <= 4000; t++) {

			if (atom.isEmpty())
				break;

			int cntAtom = atom.size();

			ArrayList<int[]> wait = new ArrayList<>();

			for (int i = 0; i < cntAtom; i++) {

				int[] curr = atom.get(i);

				int ny = curr[0] + delta[curr[2]][0];
				int nx = curr[1] + delta[curr[2]][1];

				if (ny < 0 || ny > 4000 || nx < 0 || nx > 4000) {
					continue;
				}

				wait.add(new int[] { ny, nx, curr[2], curr[3] });

			}

			Map<Integer, List<int[]>> map = new HashMap<>();

			for (int[] w : wait) {

				int key = encoding(w[0], w[1]);

				List<int[]> list = map.get(key);

				if (list == null) {
					list = new ArrayList<>();
					map.put(key, list);
				}
				list.add(w);

			}

			ArrayList<int[]> survivors = new ArrayList<>();
			for (List<int[]> w : map.values()) {

				if (w.size() > 1) {

					for (int[] a : w) {
						total += a[3];
					}

				} else {
					survivors.add(w.get(0));
				}

			}

			atom = survivors;

		}

		return total;
	}

	private static int encoding(int y, int x) {
		return (y << 12) | x;
	}
}
