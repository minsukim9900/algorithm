import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int count = 0;

		int maxLength = 0;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			count += Integer.bitCount(num);
			maxLength = Math.max(maxLength, Integer.toBinaryString(num).length() - 1);
		}
		
		System.out.println(count + maxLength);
	}
}