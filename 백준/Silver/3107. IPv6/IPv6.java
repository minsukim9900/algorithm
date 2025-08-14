import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		StringBuilder tmp = new StringBuilder();

		List<String> contraction = new ArrayList<>();
		for (int c = 0; c < str.length(); c++) {
			if (str.charAt(c) == ':') {
				contraction.add(tmp.toString());
				tmp = new StringBuilder();
			} else {
				tmp.append(str.charAt(c));
			}
		}
		contraction.add(tmp.toString());

		int count = 0;
		for (String s : contraction) {
			if (!s.equals("")) {
				count++;
			}
		}
		int zeroCount = 8 - count;
		for (String s : contraction) {
			if (s.equals("")) {
				while (zeroCount-- > 0) {
					sb.append("0000:");
				}
			} else {
				for (int i = 0; i < 4 - s.length(); i++) {
					sb.append(0);
				}
				sb.append(s).append(":");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.print(sb.toString());
	}
}