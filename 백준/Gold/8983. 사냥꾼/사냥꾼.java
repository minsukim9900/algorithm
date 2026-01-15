import java.io.*;
import java.util.*;

public class Main {
	private static int M, N, L;
	private static int[] saro;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		saro = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			saro[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(saro);

		int answer = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int x1 = saro[lowerBound(a)];
			int x2 = saro[upperBound(a)];
			
			int distance1 = Math.abs(x1 - a) + b;
			int distance2 = Math.abs(x2 - a) + b;
			
			if(distance1 <= L || distance2 <= L) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	private static int lowerBound(int a) {
		int s = 0;
		int e = M - 1;
		int answer = 0;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (saro[mid] <= a) {
				answer = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return answer;
	}

	private static int upperBound(int a) {
		int s = 0;
		int e = M - 1;
		int answer = 0;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (saro[mid] >= a) {
				answer = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		
		return answer;
	}
}