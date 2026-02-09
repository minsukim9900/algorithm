import java.io.*;
import java.util.*;

public class Main {
	private static int N, Q;
	private static List<Integer> red, blue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		red = new ArrayList<>();
		blue = new ArrayList<>();

		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			char x = str.charAt(i);
			if (x == 'R') {
				red.add(i);
			}

			if (x == 'B') {
				blue.add(i);
			}
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			int a = binarySearch(l, red);
			if (a == -1) {
				sb.append(-1).append("\n");
				continue;
			}
			int b = binarySearch(a + 1, red);
			if (b == -1) {
				sb.append(-1).append("\n");
				continue;
			}
			int c = binarySearch(b + 1, blue);
			if (c == -1) {
				sb.append(-1).append("\n");
				continue;
			}
			int d = binarySearch(c + 1, blue);
			if (d == -1) {
				sb.append(-1).append("\n");
				continue;
			}

			if (a > r || b > r || c > r || d > r) {
				sb.append(-1).append("\n");
			} else {
				sb.append(a).append(" ").append(b).append(" ").append(c).append(" ").append(d).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static int binarySearch(int start, List<Integer> color) {
		int s = 0;
		int e = color.size() - 1;
		int answer = -1;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (start <= color.get(mid)) {
				answer = color.get(mid);
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return answer;
	}
}