import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] prefix = new long[N + 1];
		prefix[0] = 0L;

		Map<Long, Long> map = new HashMap<>();
		long answer = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());

			if (prefix[i] == K)
				answer++;

			if (map.containsKey(prefix[i] - K)) {
				answer += map.get(prefix[i] - K);
			}

			if (!map.containsKey(prefix[i])) {
				map.put(prefix[i], 1L);
			} else {
				map.put(prefix[i], map.get(prefix[i]) + 1);
			}
		}

		System.out.println(answer);
	}
}
