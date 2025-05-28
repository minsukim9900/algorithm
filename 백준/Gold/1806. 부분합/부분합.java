import java.io.*;
import java.util.*;

class Main {
	private static int N, S;
	private static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int min = Integer.MAX_VALUE;
		int left = 0;
		int right = 0;
		int cnt = 1;
		int sum = nums[0];

		while (left <= right) {
			while (sum >= S && left <= right) {
				min = Math.min(min, cnt);
				sum -= nums[left++];
				cnt--;
			}
			
			if(right == N - 1) {
				break;
			}
			
			if (right + 1 < N) {
				sum += nums[++right];
				cnt++;
			}
		}

		return min == Integer.MAX_VALUE ? 0 : min;
	}
}