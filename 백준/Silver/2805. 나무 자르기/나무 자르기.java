import java.io.*;
import java.util.*;

/*
 * 상근이는 나무 M미트가 필요함.
 * 상근이는 절단기에 높이 H를 지정
 * 톱날이 H미터 올라감.
 * 한 줄에 연속해 있던 나무를 모두 절단.
 * 높이가 H보다 큰 나무는 위의 부분이 잘릴 것
 * 작은 나무는 안잘림
 * 절단기가 설정할 수 있는 높이는 0 <= 정수
 * 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
 * 나무의 높이의 합은 M보다 크거나 같다.
 */

public class Main {
	private static int N, M;
	private static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 나무
		M = Integer.parseInt(st.nextToken()); // 필요한 나무 길이

		arr = new long[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		System.out.println(binarySearch());

	}

	private static long binarySearch() {
		long s = 1;
		long e = Long.MAX_VALUE;
		long result = 0;

		while (s <= e) {
			long mid = (s >> 1) + (e >> 1);
			
			if (check(mid)) {
				result = mid;
				s = mid +1;
			} else {
				e = mid -1;
			}
		}

		return result;
	}

	private static boolean check(long v) {
		long cnt = 0;
		for (int i = 0; i < N; i++) {
			long tmp = arr[i] - v;
			if (tmp < 0)
				tmp = 0;
			cnt += tmp;

			if (cnt >= M)
				return true;
		}

		return false;
	}
}