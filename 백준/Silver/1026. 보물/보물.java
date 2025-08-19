import java.util.*;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		int[] numA = new int[N];
		int[] numB = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numA[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numA);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numB[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numB);

		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += (numA[i] * numB[N - i - 1]);
		}
		System.out.println(sum);
	}
}