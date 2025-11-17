import java.io.*;
import java.util.*;

public class Main {
	private static int D, N;
	private static int[] hole;
	private static int end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		hole = new int[D];
		st = new StringTokenizer(br.readLine());
		hole[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < D; i++) {
			hole[i] = Math.min(hole[i - 1], Integer.parseInt(st.nextToken()));
		}

		end = D - 1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int target = Integer.parseInt(st.nextToken());

			int result = binarySearch(target);

			if (result == -1) {
				System.out.println(0);
				return;
			}
			end = result - 1;
		}

		System.out.println(end + 2);
	}

	private static int binarySearch(int target) {
		int answer = -1;
		int s = 0;
		int e = end;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (hole[mid] >= target) {
				answer = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return answer;
	}
}