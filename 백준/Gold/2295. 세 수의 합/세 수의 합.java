import java.io.*;
import java.util.*;

public class Main {
	private static int N, max;
	private static int[] arr;
	private static Set<Integer> nums = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			nums.add(arr[i]);
			max = Math.max(arr[i], max);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nums.add(arr[i] + arr[j]);
			}
		}

		int answer = 0;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (nums.contains(arr[i] - arr[j])) {
					answer = Math.max(answer, arr[i]);
				}
			}
		}
		System.out.println(answer);
	}

}