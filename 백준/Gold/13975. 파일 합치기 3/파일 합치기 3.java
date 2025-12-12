import java.io.*;
import java.util.*;

public class Main {
	private static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			K = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for (int i = 0; i < K; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}

			long answer = 0L;
			while (pq.size() != 1) {
				long x = pq.poll();
				long y = pq.poll();
				long sum = x + y;
				answer += (sum);

				pq.add(sum);
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}