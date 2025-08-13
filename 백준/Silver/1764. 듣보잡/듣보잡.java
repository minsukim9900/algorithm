import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		Set<String> set = new HashSet<>();
		TreeSet<String> ts = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}

		for (int i = 0; i < M; i++) {
			String look = br.readLine();

			if (set.contains(look)) {
				ts.add(look);
			}
		}
		sb.append(ts.size()).append("\n");
		Iterator<String> iterator = ts.iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next()).append("\n");
		}

		System.out.println(sb.toString());

	}
}