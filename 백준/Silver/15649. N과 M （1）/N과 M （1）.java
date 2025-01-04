import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static boolean[] visited;
	private static int[] result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		result = new int[M];
		
		dfs(0);
		
		
	}

	private static void dfs(int depth) {
		if (depth == M) {
			
			for(int i = 0; i<M; i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			
		} else {

			for (int i = 1; i <= N; i++) {
				if (visited[i])
					continue;
				visited[i] = true;
				result[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}

		}
	}

}