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
		int left = 0, right = 0, result = 0;
		int sum = 0;
		
		while(true) {
			if(sum >= M) {
				if(sum == M) {
					result++;
				}
				sum -= nums[left++];
			} else {
				if(right == N) break;
				sum += nums[right++];
			}
		}
		return result;
	}
}