import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		long[] honey = new long[N];
		long[] leftPrefix = new long[N];
		long[] rightPrefix = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			honey[i] = Integer.parseInt(st.nextToken());
		}

		leftPrefix[0] = honey[0];
		rightPrefix[N - 1] = honey[N - 1];
		for (int i = 1; i < N; i++) {
			leftPrefix[i] = honey[i] + leftPrefix[i - 1];
			rightPrefix[N - 1 - i] = honey[N - 1 - i] + rightPrefix[N - i];
		}

		long answer = 0L;
		for (int i = 1; i < N - 1; i++) {
			answer = Math.max(answer, leftPrefix[N - 1] - leftPrefix[0] + leftPrefix[N - 1] - leftPrefix[i] - honey[i]);
		}
		for (int i = N - 2; i > 0; i--) {
			answer = Math.max(answer, rightPrefix[0] - rightPrefix[N - 1] + rightPrefix[0] - rightPrefix[i] - honey[i]);
		}
		
		for(int i = 1; i < N - 1; i++) {
			answer = Math.max(answer, leftPrefix[i] - leftPrefix[0] + rightPrefix[i] - rightPrefix[N - 1]);
		}
		
		System.out.println(answer);

	}
}