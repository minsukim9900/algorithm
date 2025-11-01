import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];

		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		String base = words[0];
		int answer = 0;

		for (int i = 1; i < N; i++) {
			if (isSimilar(base, words[i]))
				answer++;
		}
		System.out.println(answer);
	}

	private static boolean isSimilar(String base, String str) {
		if (Math.abs(base.length() - str.length()) > 1)
			return false;

		int[] cnt = new int[26];
		for (int i = 0; i < base.length(); i++) {
			cnt[base.charAt(i) - 'A']++;
		}

		for (int i = 0; i < str.length(); i++) {
			cnt[str.charAt(i) - 'A']--;
		}

		int result = 0;
		for (int x : cnt) {
			result += Math.abs(x);
		}

		if (base.length() == str.length()) {
			return result == 0 || result == 2;
		}
		return result == 1;
	}
}