import java.io.*;
import java.util.*;

public class Main {

	private static int[] result;
	private static int cnt = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		result = new int[N];
		combi(N, 0, 0);
		System.out.println(cnt);
	}

	private static void combi(int N, int depth, int visited) {

		if (depth == N) {
			
				cnt++;

		} else {

			for (int i = 0; i < N; i++) {
				if (depth >= 1 && Math.abs(result[depth - 1] - i) == 1)
					continue;
				if ((visited & (1 << i)) == 0) {
					visited |= (1 << i);
					result[depth] = i;
					if(isPut(depth)) {
						combi(N, depth + 1, visited);
					}
					visited ^= (1 << i);
				}

			}

		}
	}

	private static boolean isPut(int depth) {

		for (int i = 0; i < depth; i++) {
			if(Math.abs(result[i] - result[depth]) == Math.abs(i - depth)) return false;
		}
		
		return true;

	}
	
	

}
