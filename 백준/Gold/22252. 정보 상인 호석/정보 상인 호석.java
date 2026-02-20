import java.io.*;
import java.util.*;

public class Main {
	private static Map<String, PriorityQueue<Integer>> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int query = Integer.parseInt(br.readLine());
		long answer = 0L;
		map = new HashMap<>();

		for (int i = 0; i < query; i++) {
			st = new StringTokenizer(br.readLine());

			int order = Integer.parseInt(st.nextToken());

			if (order == 1) {
				String infoName = st.nextToken();
				PriorityQueue<Integer> pq;

				pq = map.containsKey(infoName) ? map.get(infoName)
						: new PriorityQueue<>((a, b) -> Integer.compare(b, a));

				int count = Integer.parseInt(st.nextToken());
				for (int j = 0; j < count; j++) {
					pq.add(Integer.parseInt(st.nextToken()));
				}

				map.put(infoName, pq);
			} else {
				String infoName = st.nextToken();
				PriorityQueue<Integer> pq = map.containsKey(infoName) ? map.get(infoName)
						: new PriorityQueue<>((a, b) -> Integer.compare(b, a));

				int count = Integer.parseInt(st.nextToken());

				while (!pq.isEmpty() && count-- > 0) {
					answer += pq.poll();
				}
			}
		}
		System.out.println(answer);
	}
}