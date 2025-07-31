import java.io.*;
import java.util.*;

class Solution {
	private static int N, P;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			sb.append("#").append(t).append(" ").append(simulate(N, P)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static long simulate(int n, int p) {
		if (p == 1) {
			return n;
		}
		return (n / p) * simulate(n - n / p, p - 1);
	}
}