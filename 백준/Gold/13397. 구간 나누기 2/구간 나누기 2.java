import java.io.*;
import java.util.*;;

public class Main {
	private static int N, M;
	private static int[] nums;
	private static final int INF = 20_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int s = 0;
		int e = INF;
		int answer = 0;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (check(mid)) {
				answer = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return answer;
	}

	private static boolean check(int mid) {
		int count = 1;
		int min = nums[0];
		int max = nums[0];

		for (int i = 1; i < N; i++) {
			min = Math.min(nums[i], min);
			max = Math.max(nums[i], max);

			if (max - min > mid) {
				count++;
				min = nums[i];
				max = nums[i];
				if(count > M) return false;
			}
		}
		return true;
	}
}
