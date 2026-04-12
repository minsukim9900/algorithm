import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] time = new long[N];

		for (int i = 0; i < N; i++) {
			time[i] = Long.parseLong(br.readLine());
		}

		if (K >= N) {
			System.out.println(N);
			return;
		}

		List<Long> gaps = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			gaps.add(time[i] - time[i - 1] - 1);
		}

		gaps.sort((a, b) -> Long.compare(b, a));

		long answer = time[N - 1] - time[0] + 1;

		for (int i = 0; i < K - 1; i++) {
			answer -= gaps.get(i);
		}

		System.out.println(answer);
	}
}