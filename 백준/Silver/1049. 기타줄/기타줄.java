import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static final int INF = 1001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int sixMin = INF;
		int oneMin = INF;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int six = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());

			sixMin = Math.min(sixMin, six);
			oneMin = Math.min(oneMin, one);
		}

		sixMin = Math.min(sixMin, oneMin * 6);

		int mod = N % 6;
		int cnt = N / 6;


		int answer = cnt * sixMin + sixMin;
		
		answer = Math.min(answer, cnt * sixMin + mod * oneMin);
		System.out.println(answer);
	}
}