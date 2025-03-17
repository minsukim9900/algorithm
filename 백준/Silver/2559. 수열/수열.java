import java.io.*;
import java.util.*;

public class Main {

	private static int N, K;
	private static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(cal());

	}

	private static long cal() {
		int currSum = 0;
		for (int i = 0; i < K; i++) {
			currSum += arr[i];
		}

		long max = currSum;

		for (int i = K; i < N; i++) {
			currSum += arr[i] - arr[i - K];
			max = Math.max(max, currSum);
		}

		return max;
	}

}