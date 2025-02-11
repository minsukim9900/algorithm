import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, min;
	private static ArrayList<int[]> adj = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.add(new int[] { s, b });
		}

		for (int i = 1; i <= N; i++) {
			M = i;
			comb(0, 0, 1, 0);
		}
		
		System.out.println(min);

	}

	private static void comb(int num, int depth, int b, int s) {

		if (depth == M) {
			
			min = Math.min(min, Math.abs(b - s));

		} else {
			for (int i = num; i < N; i++) {
				
				comb(i+1, depth+1, b * adj.get(i)[0], s + adj.get(i)[1]);
				
			}
		}

	}

}
