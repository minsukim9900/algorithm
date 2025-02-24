import java.io.*;
import java.util.*;

public class Main {

	private static int[] p, money, cnt;
	private static int N, M, k;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
		money = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int px = findP(x);
			int py = findP(y);
			if(money[px] <= money[py]) {
				union(px, py);
			}else {
				union(py, px);
			}
			
		}
		
		for(int i = 1; i<=N; i++) {
			p[i] = findP(p[i]);
		}
		
		
		cnt = new int[N + 1];
		for(int i = 1; i<=N; i++) {
			cnt[p[i]]++;
		}
		
		long sum = 0;
		for(int i = 1; i<=N; i++) {
			if(cnt[i] > 0) {
				sum += money[i];
			}
		}
		
		
		if(sum <= k) {
			System.out.println(sum);
		} else {
			System.out.println("Oh no");
		}

	}

	private static int findP(int x) {

		if (x != p[x]) {
			p[x] = findP(p[x]);
		}

		return p[x];
	}

	private static void union(int x, int y) {

		p[y] = x;
	}

}