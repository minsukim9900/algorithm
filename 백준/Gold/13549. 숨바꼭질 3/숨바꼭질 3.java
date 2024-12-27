import java.io.*;
import java.util.*;

public class Main {

	private static int N, K;
	private static int[] delta = { -1, 1, 2 };
	private static int dist[] = new int[100001];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			System.out.println(0);
		}else{
			bfs();
			System.out.println(dist[K]);
		}
		

	}

	private static void bfs() {

		Queue<int[]> q = new ArrayDeque<>();
		
		dist[N] = 0;
		q.offer(new int[] { N, dist[N] });
		Arrays.fill(dist, Integer.MAX_VALUE);

		while (!q.isEmpty()) {

			int[] curr = q.poll();

			for (int i = 0; i < 3; i++) {

				int nr = 0;

				if (i == 2) {
					nr = curr[0] * delta[i];

					if (nr >= 0 && nr <= 100000) {

						int tmp = curr[1];
						if (dist[nr] > tmp) {
							dist[nr] = tmp;
							q.offer(new int[] { nr, dist[nr] });
						}

					}

				} else {

					nr = curr[0] + delta[i];

					if (nr >= 0 && nr <= 100000) {

						int tmp = curr[1] + 1;
						if (dist[nr] > tmp) {
							dist[nr] = tmp;
							q.offer(new int[] { nr, dist[nr] });
						}

					}

				}

			}

		}

	}

}