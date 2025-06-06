import java.io.*;
import java.util.*;

class Main {
	private static int n, s, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		n -= s;
		m = Integer.parseInt(br.readLine());
		int[] times = new int[m + 1];

		for (int i = 1; i <= m; i++) {
			times[i] = Integer.parseInt(br.readLine());
		}

		int idx = 0;
		out: for (int i = 0; i <= 100000; i++) {

			for (int j = 1; j <= m; j++) {
				if (i % times[j] == 0) {
					n--;
					idx = j;
					if (n <= 0) {
						break out;
					}
				}
			}
		}

		System.out.println(idx);
	}
}