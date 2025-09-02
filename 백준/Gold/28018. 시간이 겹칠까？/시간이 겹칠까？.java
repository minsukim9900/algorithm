import java.io.*;
import java.util.*;;

public class Main {
	private static int N, Q;
	private static int[] states;
	private static int[] prefix;
	private static final int MAX_TIME = 1_000_002;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		states = new int[MAX_TIME];
		prefix = new int[MAX_TIME];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			states[s]++;
			states[e + 1]--;
		}
		
		for(int i = 1; i < MAX_TIME; i++) {
			prefix[i] = prefix[i - 1] + states[i];
		}
		
		Q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			sb.append(prefix[Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
