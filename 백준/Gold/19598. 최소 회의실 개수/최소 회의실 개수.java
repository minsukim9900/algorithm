import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int[][] info = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			info[i][0] = start;
			info[i][1] = end;
		}
		Arrays.sort(info, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(info[0][1]);
		for (int i = 1; i < N; i++) {
			if (pq.peek() <= info[i][0]) {
				pq.poll();
			}
			pq.add(info[i][1]);
		}
		System.out.println(pq.size());
	}
}