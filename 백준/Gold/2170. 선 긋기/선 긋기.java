import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			pq.add(new int[] { x, y });
		}

		int[] curr = pq.poll();
		int preStart = curr[0];
		int preEnd = curr[1];
		int answer = preEnd - preStart;

		while (!pq.isEmpty()) {
			curr = pq.poll();

			int start = curr[0];
			int end = curr[1];

			if (start >= preStart && start <= preEnd) {
				int plus = end - preEnd;

				if (plus > 0) {
					answer += plus;
					preEnd = end;
				}
			} else if (preEnd < start) {
				answer += end - start;
				preEnd = end;
				preStart = start;
			}
		}
		System.out.println(answer);
	}
}