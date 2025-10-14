import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		int[] count = new int[500_001];
		for (int i = 0; i < N; i++) {
			count[Integer.parseInt(br.readLine())]++;
		}

		long B = 1L;
		long ans = 0L;
		for (int i = 1; i <= 500_000; i++) {
			while (count[i] > 0) {
				ans += (long) Math.abs(i - B++);
				count[i]--;
			}
		}
		System.out.println(ans);
	}
}
