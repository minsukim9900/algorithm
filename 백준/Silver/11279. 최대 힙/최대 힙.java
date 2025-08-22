import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				int result = 0;
				while (!pq.isEmpty()) {
					result = pq.poll();
					break;
				}
				sb.append(result).append("\n");
			} else {
				pq.add(num);
			}
		}
		System.out.println(sb.toString());
	}
}
