import java.io.*;
import java.util.*;

public class Main {

	private static int[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		visited = new int[(N >> 5) + 1];

		int[][] lec = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			lec[i][0] = Integer.parseInt(st.nextToken());
			lec[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(lec, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {

				return o1[0] - o2[0];
			}
		});
		

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(lec[0][1]);

		for (int i = 1; i < N; i++) {

			if (pq.peek() <= lec[i][0]) {
				pq.poll();
			}
			pq.add(lec[i][1]);

		}
		
		System.out.println(pq.size());

	}

}