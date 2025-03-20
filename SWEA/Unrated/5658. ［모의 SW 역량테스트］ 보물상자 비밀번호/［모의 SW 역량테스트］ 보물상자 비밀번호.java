import java.io.*;
import java.util.*;

import javax.lang.model.type.UnionType;

public class Solution {
	private static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String pw = br.readLine();
			Set<Integer> nums = simulate(pw);
			Integer[] arr = new Integer[nums.size()];
			int idx = 0;

			Iterator<Integer> iter = nums.iterator();
			while (iter.hasNext()) {
				arr[idx++] = iter.next();
			}
			Arrays.sort(arr, Collections.reverseOrder());
			sb.append("#" + t + " " + arr[K - 1] + "\n");
		}
		System.out.println(sb.toString());
	}

	private static Set<Integer> simulate(String pw) {

		char[] cr = new char[N];

		for (int i = 0; i < N; i++) {
			cr[i] = pw.charAt(i);
		}

		Set<Integer> nums = new HashSet<>();

		cal(nums, cr,0);
		for (int i = 0; i < (N >> 2) - 1; i++) {
			cal(nums, cr, i + 1);
		}

		return nums;
	}

	private static Set<Integer> cal(Set<Integer> nums, char[] cr, int cir) {
		for (int i = 0; i < N; i += (N >> 2)) {
			int num = 0;

			for (int j = 0; j < (N >> 2); j++) {
				int idx = (i + j - cir + N) % N;
				int tmp = Character.digit(cr[idx], 16);
				int x = 1;

				for (int k = 0; k < (N >> 2) - j - 1; k++) {
					x *= 16;
				}
				num += (tmp * x);
			}
			nums.add(num);
		}
		return nums;
	}

}