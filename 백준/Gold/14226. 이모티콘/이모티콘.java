import java.io.*;
import java.util.*;

public class Main {
	private static int S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		S = Integer.parseInt(br.readLine());
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		int[][] dist = new int[2001][2001];

		for (int r = 0; r < 2001; r++) {
			Arrays.fill(dist[r], -1);
		}
		dist[1][0] = 0;
		q.add(new int[] { 1, dist[1][0] });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int count = curr[0];
			int copy = curr[1];
			
			if(count == S) {
				return dist[count][copy];
			}

			if (dist[count][count] == -1) {
				dist[count][count] = dist[count][copy] + 1;
				q.add(new int[] { count, count});
			}

			int nCount = count + copy;
			if (nCount <= 2000 && dist[nCount][copy] == -1) {
				dist[nCount][copy] = dist[count][copy] + 1;
				q.add(new int[] {nCount, copy});
			}
			
			nCount = count - 1;
			if(nCount > 0 && nCount <= 2000 && dist[nCount][copy] == -1 ) {
				dist[nCount][copy] = dist[count][copy] + 1;
				q.add(new int[] {nCount, copy});
			}

		}
		return -1;
	}
}