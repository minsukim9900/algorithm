import java.io.*;
import java.util.*;

public class Main {

	private static int N, K;
	private static int[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(cal());

	}

	private static int cal() {

		int[] diff = new int[N - 1];

		for (int i = 0; i < N - 1; i++) {
			diff[i] = nums[i + 1] - nums[i];
		}

		Arrays.sort(diff);

		int total = nums[N - 1] - nums[0];

		for (int i = 0; i < K - 1; i++) {

			total -= diff[N - 2 - i];

		}

		return total;
	}

}
