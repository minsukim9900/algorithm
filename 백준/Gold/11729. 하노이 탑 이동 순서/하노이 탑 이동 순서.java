import java.io.*;
import java.util.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		sb.append((int) Math.pow(2, N) - 1).append("\n");
		hanoi(N, 1, 2, 3);
		System.out.println(sb.toString());
	}

	private static void hanoi(int n, int s, int mid, int e) {
		if (n == 1) {
			sb.append(s).append(" ").append(e).append("\n");
			return;
		}

		hanoi(n - 1, s, e, mid);
		sb.append(s).append(" ").append(e).append("\n");
		hanoi(n - 1, mid, s, e);
	}
}