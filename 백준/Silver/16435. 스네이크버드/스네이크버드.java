import java.io.*;
import java.util.*;

public class Main {
	private static int N, L;
	private static int[] H;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		H = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(H);

		int max = L;
		for (int i = 0; i < N; i++) {
			if (max >= H[i]) {
				max++;
			} else {
				break;
			}
		}

		System.out.println(max);
	}
}