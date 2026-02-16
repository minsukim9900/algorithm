import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String number = br.readLine();
		long length = number.length();
		long num = Long.parseLong(number);

		long mod = 1L;
		long answer = 0L;
		long digit = 1;
		long count = 9;

		for (int i = 0; i < length - 1; i++) {
			answer += (digit * count);
			digit++;
			count *= 10;
			mod *= 10;
		}

		System.out.println((num - mod + 1) * length + answer);
	}
}