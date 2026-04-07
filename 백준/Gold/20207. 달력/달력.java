import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static final int DAY_END = 366;
	private static int[] count = new int[DAY_END];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			for (int day = start; day < end + 1; day++) {
				count[day]++;
			}
		}

		long answer = 0;

		int width = 0;
		int height = 0;
		for (int i = 1; i < DAY_END; i++) {
			if (count[i] == 0) {
				answer += (width * height);
				width = 0;
				height = 0;
				continue;
			}

			width++;
			height = Math.max(height, count[i]);
		}

		answer += (width * height);
		System.out.println(answer);
	}
}