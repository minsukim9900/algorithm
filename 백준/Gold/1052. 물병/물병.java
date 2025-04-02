import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(simulate());
	}

	private static int simulate() {
		int cnt = 0;
		int bottle = N;
		
		while(Integer.bitCount(bottle) > K) {
			cnt++;
			bottle = N + cnt;
		}

		return cnt;
	}
}