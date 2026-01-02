import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, index;
	private static Map<String, Integer> map;
	private static Set<String> set;
	private static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		map = new HashMap<>();
		set = new HashSet<>();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		index = 0;

		count = new int[N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (str.length() < M)
				continue;

			if (map.containsKey(str)) {
				count[map.get(str)]++;
				continue;
			}

			set.add(str);
			map.put(str, index++);
		}
		PriorityQueue<String> pq = new PriorityQueue<>(

				(a, b) -> count[map.get(a)] == count[map.get(b)]
						? a.length() == b.length() ? a.compareTo(b) : Integer.compare(b.length(), a.length())
						: Integer.compare(count[map.get(b)], count[map.get(a)]));
		for (String str : set) {
			pq.add(str);
		}
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}