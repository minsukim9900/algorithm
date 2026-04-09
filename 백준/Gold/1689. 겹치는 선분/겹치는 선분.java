
import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int[] start = new int[N];
		int[] end = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			start[i] = Integer.parseInt(st.nextToken());
			end[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(start);
		Arrays.sort(end);

		System.out.println(twoPointer(start, end));
	}

	private static int twoPointer(int[] start, int[] end) {
		int max = 0;
		int s = 0;
		int e = 0;
		int line = 0;

		while (s < N) {
			if (start[s] < end[e]) {
				line++;
				s++;
			} else {
				max = Math.max(max, line);
				line--;
				e++;
			}
		}

		max = Math.max(max, line);
		return max;
	}
}