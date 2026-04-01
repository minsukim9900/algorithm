import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static final int MAX = 1_000_002;
	private static int[] numberCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		numberCount = new int[MAX];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (numberCount[num + 1] > 0) {
				numberCount[num + 1]--;
			}
			numberCount[num]++;
		}

		int answer = 0;
		for (int i = 0; i < MAX - 1; i++) {
			answer += numberCount[i];
		}

		System.out.println(answer);
	}
}