import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());

		int sum = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			sum += nums[i];
		}

		System.out.println(cal(nums, sum));
	}

	private static int cal(int[] nums, int sum) {
		int count = 0;

		while (sum != 0) {
			sum = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == 0)
					continue;

				if (nums[i] % 2 == 1) {
					count++;
					nums[i]--;
				}

				nums[i] /= 2;
				sum += nums[i];
			}
			count++;
		}
		return count == 0 ? count : count - 1;
	}
}