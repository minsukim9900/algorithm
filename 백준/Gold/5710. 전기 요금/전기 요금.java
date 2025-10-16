import java.io.*;
import java.util.*;

public class Main {
	private static int[] used = { 100, 9900, 990000 };
	private static int[] fees = { 2, 3, 5, 7 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			if (A == 0)
				break;
			long x = changeWatt(A);
			System.out.println(usedElectricAmount(binarySearch(x, B)));
		}
		System.out.println(sb.toString());
	}

	private static long binarySearch(long max, int B) {
		long answer = 0L;
		long s = 0L;
		long e = max;

		while (s <= e) {
			long mid = (s + e) / 2;

			if (check(mid, max, B)) {
				answer = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return answer;
	}

	private static boolean check(long mid, long max, int B) {
		long moneyA = usedElectricAmount(mid);
		long moneyB = usedElectricAmount(max - mid);
		if (Math.abs(moneyB - moneyA) > B) {
			return false;
		}
		return true;
	}

	private static long usedElectricAmount(long x) {
		long bill = 0L;
		for (int i = 0; i < 3; i++) {
			long tmp = Math.min(x, used[i]);
			bill += (tmp * fees[i]);
			x -= tmp;
			if (x <= 0)
				return bill;
		}
		bill += (x * fees[3]);
		return bill;
	}

	private static long changeWatt(long x) {
		long money = 0L;
		long tmp = Math.min(x, fees[0] * used[0]);
		money += (tmp / fees[0]);
		x -= tmp;

		if (x == 0)
			return money;

		tmp = Math.min(x, fees[1] * used[1]);
		money += (tmp / fees[1]);
		x -= tmp;

		if (x == 0)
			return money;

		tmp = Math.min(x, fees[2] * used[2]);
		money += (tmp / fees[2]);
		x -= tmp;

		if (x == 0)
			return money;

		money += (x / fees[3]);
		return money;
	}
}
