import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(twopointer());
	}

	private static int twopointer() {
		int result = 0;
		int left = 0;
		int right = 0;
		int sum = nums[left];

		while (left < N) {
			boolean isPoss = false;

			if (sum == M) {
				result++;
				if (left + 1 < N) {
					sum -= nums[left++];
					isPoss = true;
				}
			} else if (sum > M) {
				if (left + 1 < N) {
					sum -= nums[left++];
					isPoss = true;
				}
			} else {
				if (right + 1 < N) {
					sum += nums[++right];
					isPoss = true;
				}
			}
			
			if(!isPoss) {
				break;
			}
		}
		return result;
	}
}