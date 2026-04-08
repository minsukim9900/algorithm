import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] times = new int[N];

		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(times);

		for (int i = times.length - 1; i >= 0; i--) {
			int time = times[i];

			if (pq.size() < M) {
				pq.add(time);
			} else {
				int endTime = pq.poll();
				pq.add(endTime + time);
			}
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			answer = pq.poll();
		}

		System.out.println(answer);
	}
}