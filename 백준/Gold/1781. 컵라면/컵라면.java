import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		List<int[]> info = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());

			info.add(new int[] { deadline, count });
		}
		info.sort((a, b) -> Integer.compare(a[0], b[0]));

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int[] curr : info) {
			int deadline = curr[0];
			int count = curr[1];

			pq.add(count);

			if (pq.size() > deadline) {
				pq.poll();
			}
		}

		long answer = 0L;
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}
		System.out.println(answer);
	}
}