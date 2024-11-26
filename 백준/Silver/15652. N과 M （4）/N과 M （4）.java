import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[M + 1];

		permutation(1, 1);
		System.out.println(sb.toString());

	}

	private static void permutation(int num, int depth) {

		if (depth == M + 1) {

			for (int i = 1; i <= M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			

		} else {
			for (int i = num; i <= N; i++) {
				result[depth] = i;
				permutation(i , depth + 1);
			}

		}

	}

}
