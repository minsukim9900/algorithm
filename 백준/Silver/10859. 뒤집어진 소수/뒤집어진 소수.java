import java.io.*;
import java.util.*;

public class Main {
	private static int[] rotate = new int[10];
	static {
		rotate[0] = 0;
		rotate[1] = 1;
		rotate[2] = 2;
		rotate[3] = -1;
		rotate[4] = -1;
		rotate[5] = 5;
		rotate[6] = 9;
		rotate[7] = -1;
		rotate[8] = 8;
		rotate[9] = 6;
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

			int v = rotate[tmp];

			sb.append(v);
		}
		Long num = Long.parseLong(sb.toString());
		String ans = "no";
		if (isDecimal(num) && isDecimal(Long.parseLong(str))) {
			ans = "yes";
		}
		System.out.println(ans);

	}

	private static boolean isDecimal(long num) {
		if (num < 2) {
			return false;
		}
		
		if(num == 2 || num == 3) return true;
		if(num % 2 == 0 || num % 3 == 0) return false;

		for(long i = 5; i * i <= num; i += 6) {
			if(num % i == 0 || num % (i + 2) == 0) return false;
		}
		
		return true;
	}

}