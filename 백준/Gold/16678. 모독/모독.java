
import java.io.*;
import java.util.*;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nums);
		long answer = 0L;
		int num = 1;

		for (int i = 0; i < N; i++) {
			if (nums[i] - num < 0) {
				continue;
			}

			answer += (nums[i] - num++);
		}

		System.out.println(answer);
	}
}