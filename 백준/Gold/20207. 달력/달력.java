import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static final int MAX_DAY = 367;
	private static int[] count = new int[MAX_DAY];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			count[start]++;
			count[end + 1]--;
		}

		long answer = 0;

		int width = 0;
		int height = 0;
		int current = 0;

		for (int day = 1; day < MAX_DAY; day++) {
			current += count[day];

			if (current == 0) {
				answer += (long) width * height;
				width = 0;
				height = 0;
				continue;
			}

			width++;
			height = Math.max(height, current);
		}

		answer += (width * height);
		System.out.println(answer);
	}
}