import java.io.*;
import java.util.*;

public class Main {

	private static int N, K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(bino());

	}

	private static int bino() {

		int[][] B = new int[N + 1][K + 1];

		for (int i = 0; i <= N ; i++) {

			for (int j = 0; j <= Math.min(i, K); j++) {

				if (j == 0 || j == i) {
					B[i][j] = 1;
				} else {
					B[i][j] = (B[i - 1][j - 1] + B[i - 1][j]) % 10007;
				}

			}
		}

		return B[N][K];

	}

}
