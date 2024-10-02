import java.io.*;
import java.util.*;

public class Main {

	private static int count[] = new int[20000001];
	private static Set<Integer> num = new HashSet<>();
	private static StringBuilder sb = new StringBuilder();

	public static void cntCard(int tmp) {

		if (num.contains(tmp)) {
			sb.append(count[tmp]).append(" ");
		} else {
			sb.append(0).append(" ");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int tmp = 10000000 + Integer.parseInt(st.nextToken());
			num.add(tmp);
			count[tmp]++;
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			int tmp = 10000000 + Integer.parseInt(st.nextToken());
			cntCard(tmp);
		}
		System.out.println(sb.toString());
	}
}