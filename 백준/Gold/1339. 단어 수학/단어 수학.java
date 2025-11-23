import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		boolean[] used = new boolean['Z' - 'A' + 1];
		int[] nums = new int['Z' - 'A' + 1];

		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			int tmp = 1;
			for (int j = c.length - 1; j >= 0; j--) {
				used[c[j] - 'A'] = true;
				nums[c[j] - 'A'] += tmp;
				tmp *= 10;
			}
		}

		List<int[]> info = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (used[i]) {
				info.add(new int[] { i, nums[i] });
			}
		}

		info.sort((a, b) -> Integer.compare(b[1], a[1]));
		int num = 9;
		long answer = 0L;
		for (int[] curr : info) {
			answer += (num-- * nums[curr[0]]);
		}
		System.out.println(answer);
	}
}