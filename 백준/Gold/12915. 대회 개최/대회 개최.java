import java.io.*;
import java.util.*;

public class Main {
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		nums = new int[5];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 5; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int answer = 0;
		int s = 0;
		int e = 100_000_000;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (check(mid)) {
				answer = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return answer;
	}

	private static boolean check(int mid) {

		int a = nums[0], L = nums[1], m = nums[2], R = nums[3], c = nums[4];

		int needL = Math.max(0, mid - a);
		if (needL > L)
			return false;
		L -= needL;

		int needR = Math.max(0, mid - c);
		if (needR > R)
			return false;
		R -= needR;

		int needM = Math.max(0, mid - m);
		return needM <= L + R;
	}
}