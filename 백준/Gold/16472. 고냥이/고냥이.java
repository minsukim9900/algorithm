import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		char[] s = br.readLine().toCharArray();
		int[] cnt = new int[26];
		int distinct = 0;

		int left = 0;
		int right = 0;
		int max = 0;

		while (right < s.length) {
			int r = s[right] - 'a';
			if (cnt[r] == 0)
				distinct++;
			cnt[r]++;
			right++;

			while (distinct > N) {
				int l = s[left] - 'a';
				cnt[l]--;
				if (cnt[l] == 0)
					distinct--;
				left++;
			}
			max = Math.max(max, right - left);
		}
		System.out.println(max);
	}
}