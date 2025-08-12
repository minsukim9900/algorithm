import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		solve(0, 0, 0);
		System.out.println(answer);
	}

	private static void solve(int curr, int diag1, int diag2) {
		if (curr == (1 << N) - 1) {
			answer++;
			return;
		}

		int possible = ~(curr | diag1 | diag2) & ((1 << N) - 1);

		while (possible != 0) {
			int u = possible & -possible;
			possible -= u;
			solve(curr | u, (diag1 | u) << 1, (diag2 | u) >> 1);
		}
	}
}