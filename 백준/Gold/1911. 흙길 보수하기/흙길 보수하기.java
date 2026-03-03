import java.io.*;
import java.util.*;

public class Main {
	private static int N, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		List<int[]> road = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			road.add(new int[] { start, end });
		}

		road.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

		int preStart = 0;
		int answer = 0;

		for (int[] info : road) {
			int start = Math.max(info[0], preStart);
			int end = info[1];
			int length = end - start;

			if (length <= 0)
				continue;

			answer += length / L;
			int mod = length % L;

			if (mod > 0) {
				answer++;
                preStart = end + (L - mod);
			} else {
                preStart = end;            
            }
		}

		System.out.println(answer);
	}
}