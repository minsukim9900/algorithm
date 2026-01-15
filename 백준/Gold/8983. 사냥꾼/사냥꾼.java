import java.io.*;
import java.util.*;

public class Main {
	private static int M, N, L;
	private static int[] saro;
	private static List<int[]> arr;
	private static final int END = 1_000_000;

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

		arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr.add(new int[] { x, y });
		}

		arr.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int s = 0;
		int e = END;
		int answer = 0;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (check(mid)) {
				answer = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return answer;
	}

	private static boolean check(int value) {
		int count = 0;
		int index = 0;
		for (int i = 0; i < M; i++) {
			int x = saro[i];

			for (int j = index; j < N; j++) {
				int[] curr = arr.get(j);
				int a = curr[0];
				int b = curr[1];
				int distance = Math.abs(x - a) + b;

				if (distance <= L) {
					count++;
					index = j + 1;

					if (count >= value) {
						return true;
					}
				}
			}
		}
		return false;
	}
}