import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] prefix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		prefix = new int[N + 1];
		int[] count = new int[1001];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			long num = Long.parseLong(st.nextToken());
			prefix[i] = (int) (prefix[i - 1] + num) % M;
			count[prefix[i]]++;
		}
		
		long answer = count[0];
		for(int i = 0; i < count.length; i++) {
			answer += (long) count[i] * (count[i] - 1)/ 2;
		}
		System.out.println(answer);
	}
}