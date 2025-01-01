import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[M];

		comp(1, 0);
		System.out.println(sb);
		
		
	}

	private static void comp(int idx, int depth) {

		if (depth == M) {
			
			for(int i = 0; i<result.length; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			
			return;
		}

		for (int i = idx; i <= N; i++) {
			result[depth] = i;
			comp(i + 1, depth + 1);
		}

	}
}