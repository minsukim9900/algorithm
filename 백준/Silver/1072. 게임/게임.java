import java.io.*;
import java.util.*;

public class Main {
	private static int X, Y, Z;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		Z = (int) ((long) Y * 100 / X);

		System.out.println(lowerBound());
	}

	private static int lowerBound() {
		int s = 1;
		int e = 1000000000;
		int result = -1;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (check(mid)) {
				result = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return result;
	}

	private static boolean check(int mid) {
		int x = X + mid;
		int y = Y + mid;
		int z = (int) ((long) y * 100 / x);

		if (z > Z) {
			return true;
		} else {
			return false;
		}
	}
}