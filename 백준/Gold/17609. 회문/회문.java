import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			sb.append(check(str)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int check(String str) {
		int s = 0;
		int e = str.length() - 1;
		while (s < e) {
			if(str.charAt(s) == str.charAt(e)) {
				s++;
				e--;
			} else {
				if(isPoss(str, s + 1, e) || isPoss(str, s, e - 1)) return 1;
				return 2;
			}
		}
		return 0;
	}

	private static boolean isPoss(String str, int s, int e) {

		while (s < e) {
			if (str.charAt(s) != str.charAt(e)) {
				return false;
			}
			s++;
			e--;
		}
		return true;
	}
}
