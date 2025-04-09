import java.io.*;
import java.util.*;

public class Main {
	private static HashMap<Integer, Integer> map = new HashMap<>();
	static {
		map.put(0, 0);
		map.put(1, 1);
		map.put(2, 2);
		map.put(5, 5);
		map.put(6, 9);
		map.put(8, 8);
		map.put(9, 6);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str = br.readLine();
		for (int i = str.length() - 1; i >= 0; i--) {
			int tmp = str.charAt(i) - '0';

			if (tmp == 3 || tmp == 4 || tmp == 7) {
				System.out.println("no");
				return;
			}
			
			int v = map.get(tmp);

			sb.append(v);
		}
		Long num = Long.parseLong(sb.toString());
		String ans = "no";
		if(isDecimal(num) && isDecimal(Long.parseLong(str))) {
			ans = "yes";
		}
		System.out.println(ans);

	}

	private static boolean isDecimal(long num) {
		if(num < 2) {
			return false;
		}

		for (long i = 2L; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}