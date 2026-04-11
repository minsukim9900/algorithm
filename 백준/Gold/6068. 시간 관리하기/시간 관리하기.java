import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] times = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			times[i][0] = Integer.parseInt(st.nextToken());
			times[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(times, (a, b) -> Integer.compare(b[1], a[1]));

		int currentTime = Integer.MAX_VALUE;

		for (int[] time : times) {
			currentTime = Math.min(time[1], currentTime);

			currentTime -= time[0];

			if (currentTime < 0) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(currentTime);
	}
}