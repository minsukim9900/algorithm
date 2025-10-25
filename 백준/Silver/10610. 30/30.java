import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		char[] info = br.readLine().toCharArray();
		boolean isZero = false;
		int sum = 0;
		for (char c : info) {
			int num = c - '0';
			sum += num;

			if (num == 0)
				isZero = true;
		}

		if (sum > 0 && sum % 3 == 0 && isZero) {
			Arrays.sort(info);
			for(int i = info.length - 1; i >= 0; i--) {
				sb.append(info[i]);
			}
		} else {
			sb.append(-1);
		}
		
		System.out.println(sb.toString());
		
	}
}
