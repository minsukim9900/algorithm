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
		long max = Integer.MIN_VALUE;

		int left = 0;
		int right = left + K - 1;

		while (left != N - 1 && right < N) {

			long sum = 0;

			while (left <= right) {
				sum += arr[right--];
			}
			left++;
			right = left + K - 1;
			
			max = Math.max(max, sum);

		}

		return max;
	}

}