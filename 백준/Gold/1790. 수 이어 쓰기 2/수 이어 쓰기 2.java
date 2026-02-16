import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());

		long digit = 1;
		long start = 1;
		long count = 9;

		while (K > digit * count) {
			K -= digit * count;
			digit++;
			start *= 10;
			count *= 10;
		}

		long number = start + (K - 1) / digit;

		if (number > N) {
			System.out.println(-1);
			return;
		}

		String str = Long.toString(number);
		System.out.println(str.charAt((int) ((K - 1) % digit)));
	}
}