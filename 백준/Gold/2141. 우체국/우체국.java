import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][2];

		long sum = 0L;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			sum += info[i][1];
		}
		
		Arrays.sort(info, (a, b) -> Integer.compare(a[0], b[0]));
		
		long mid = (sum + 1) / 2;
		long prefix = 0L;
		int answer = 0;

		for (int i = 0; i < N; i++) {
			prefix += info[i][1];

			if (prefix >= mid) {
				answer = info[i][0];
				break;
			}
		}

		System.out.println(answer);
	}
}