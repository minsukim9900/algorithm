import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		char[] s = br.readLine().toCharArray();
		int[] alpha = new int[26];
		int cnt = 0;

		int left = 0;
		int right = 0;
		int answer = 0;

		while (right < s.length) {
			if (alpha[s[right++] - 'a']++ == 0)
				cnt++;
			
			while (cnt > N) {
				if (--alpha[s[left] - 'a'] == 0)
					cnt--;
				left++;
			}
			answer = Math.max(answer, right - left);
		}
		System.out.println(answer);
	}
}