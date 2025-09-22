import java.io.*;
import java.util.*;;

public class Main {
	private static List<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] price = new int[N];
		int[] dis = new int[N - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			dis[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}

		long min = price[0];
		long answer = dis[0] * min;

		for (int i = 1; i < N - 1; i++) {
			min = Math.min(min, price[i]);
			answer += (min * dis[i]);
		}
		System.out.println(answer);
	}
}