import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] p;

	public static void main(String[] args) throws IOException {
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int numA = Integer.parseInt(st.nextToken());
			int numB = Integer.parseInt(st.nextToken());
			union(findP(numA), findP(numB));
		}

		int[] cnt = new int[N + 1];
        
		for (int i = 1; i < p.length; i++) {
            p[i] = findP(p[i]);
			cnt[p[i]]++;
		}

		long sum = 1;
		for (int i = 1; i < cnt.length; i++) {
			if (cnt[i] > 0) {
				sum = (sum * cnt[i]) % 1000000007;
			}
		}

		System.out.println(sum);
	}

	private static void union(int A, int B) {
		p[B] = A;
	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}

		return p[x];
	}

}
