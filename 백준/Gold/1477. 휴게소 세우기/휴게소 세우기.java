import java.io.*;
import java.util.*;

public class Main {
    
    private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	private static int N, M, L;
	private static int[] arr;
	private static int[] dif;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = read();
		M = read();
		L = read();

		arr = new int[N + 2];
		arr[0] = 0;
		arr[N + 1] = L;
		cnt = M;

		if (N > 0) {
			for (int i = 1; i <= N; i++) {
				arr[i] = read();
			}
		}

		Arrays.sort(arr);
		dif = new int[N + 1];

		for (int i = 0; i < arr.length - 1; i++) {
			dif[i] = arr[i + 1] - arr[i];
		}

		System.out.println(binarySerach());

	}

	private static int binarySerach() {
		int s = 1;
		int e = L;
		int result = 0;

		while (s <= e) {
			int mid = s + ((e - s) >> 1);

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
		int count = 0;

		for (int i = 0; i <= N; i++) {
			count += ((dif[i] - 1) / mid);
		}

		return count <= cnt;
	}

}