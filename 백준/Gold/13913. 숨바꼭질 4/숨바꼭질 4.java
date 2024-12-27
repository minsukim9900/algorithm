import java.io.*;
import java.util.*;

public class Main {

	private static int N, K;
	private static int[] delta = { -1, 1, 2 };
	private static boolean[] visited = new boolean[100001];
	private static int[] p = new int[100001];
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (N == K) {
			System.out.println(0);
			System.out.println(N);
		} else {
			bfs();
			quest();
		}
		
		
	}
	
	private static void quest() {
		int idx = K;
		Stack<Integer> result = new Stack<>();
		result.push(K);
		while(p[idx] != -1) {
			result.push(p[idx]);
			
			idx = p[idx];
		}
		
		while(!result.isEmpty()) {
			System.out.print(result.pop() + " ");
		}
		
	}
	

	private static void bfs() {

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { N, 0 });
		visited[N] = true;
		p[N] = 0;
		Arrays.fill(p, -1);
		
		
		while (!q.isEmpty()) {

			int[] curr = q.poll();
			
			if(curr[0] == K) {
				System.out.println(curr[1]);
				return;
			}
			
			for (int i = 0; i < 3; i++) {

				int nr = 0;

				if (i == 2) {
					nr = curr[0] * delta[i];
				} else {
					nr = curr[0] + delta[i];
				}

				if (nr >= 0 && nr <= 100000 && !visited[nr]) {
					visited[nr] = true;
					p[nr] = curr[0];
					q.offer(new int[] { nr, curr[1] + 1 });
				}

			}

		}

	}

}