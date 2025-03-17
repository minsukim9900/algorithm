import java.io.*;
import java.util.*;

public class Main {

	private static int N, K, B;
	private static int[] lights;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		lights = new int[N + 1];

		for (int i = 0; i < B; i++) {
			int idx = Integer.parseInt(br.readLine());
			lights[idx] = 1;
		}
		
		System.out.println(cal());

	}

	private static int cal() {
		int currCnt = 0;

		for (int i = 1; i <= K; i++) {
			currCnt += lights[i];
		}

		int min = currCnt;

		for (int i = K + 1; i <= N; i++) {
			currCnt += lights[i] - lights[i - K];
			min = Math.min(currCnt, min);
		}

		return min;
	}

}