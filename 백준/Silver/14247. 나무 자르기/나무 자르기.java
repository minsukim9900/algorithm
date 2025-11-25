import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] H = new int[N];

		long answer = 0L;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			answer += Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(H);
		for (int i = 0; i < N; i++) {
			long sum = H[i] * i;
			answer += sum;
		}
		System.out.println(answer);
	}
}