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
		int p = Integer.parseInt(st.nextToken());
		int answer = p * dis[0];

		for (int i = 1; i < N - 1; i++) {
			p = Math.min(p, Integer.parseInt(st.nextToken()));
			answer += p * dis[i];
		}
		System.out.println(answer);
	}
}