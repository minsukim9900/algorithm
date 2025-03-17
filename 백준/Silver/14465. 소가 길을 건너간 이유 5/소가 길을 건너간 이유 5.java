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

		lights = new int[(N >> 5) + 1];

		for (int i = 0; i < B; i++) {
			int idx = Integer.parseInt(br.readLine());
			lights[idx >> 5] |= 1 << (idx & 31);
		}
		
		System.out.println(cal());

	}

	private static int cal() {
		int currCnt = 0;

		for (int i = 1; i <= K; i++) {
			if ((lights[i >> 5] & (1 << (i & 31))) != 0) {
				currCnt++;
			}
		}

		int min = currCnt;

		for (int i = K + 1; i <= N; i++) {
			int a = 0;
			int b = 0;

			if ((lights[(i - K) >> 5] & (1 << ((i - K) & 31))) != 0) {
				a = 1;
			}
			if ((lights[i >> 5] & (1 << (i & 31))) != 0) {
				b = 1;
			}
			
			currCnt += b - a;
			min = Math.min(min, currCnt);
		}
		
		return min;

	}

}