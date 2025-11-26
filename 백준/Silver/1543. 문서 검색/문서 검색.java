import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str = br.readLine();
		String comp = br.readLine();
		int strLen = str.length();
		int compLen = comp.length();
		int answer = 0;
		if (strLen < compLen) {
			System.out.println(answer);
			return;
		}
		
		int idx = 0;
		while (idx <= strLen - compLen) {
			
			String result = str.substring(idx, idx + compLen);
			if (check(result, comp)) {
				idx += compLen;
				answer++;
			} else {
				idx++;
			}
		}
		System.out.println(answer);
	}

	private static boolean check(String str1, String str2) {
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i))
				return false;
		}
		return true;
	}
}