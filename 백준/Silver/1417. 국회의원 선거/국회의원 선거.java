import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] nums;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int idx = -1;
		while (idx != 0) {
			int max = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (max < nums[i]) {
					max = nums[i];
					idx = i;
				}
			}

			if (idx != 0) {
				opt(idx);
				cnt++;
			}
		}

		System.out.println(cnt);

	}

	public static void opt(int idx) {
		nums[idx]--;
		nums[0]++;
	}

}
