import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] result;
	private static int visited;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		result = new int[N];
		
		perm(0);
		System.out.println(sb.toString());

	}

	private static void perm(int depth) {

		if (depth == N) {
			
			for(int w : result) {
				sb.append(w).append(" ");
			}
			sb.append("\n");
			
		} else {

			for (int i = 1; i <= N; i++) {

				if ((visited & (1 << i)) == 0) {
					visited |= (1 << i);
					result[depth] = i;
					perm(depth + 1);
					visited ^= (1 << i);
				}

			}

		}

	}

}
