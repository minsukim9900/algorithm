import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, L;
	private static int[] arr;
	private static int[] dif;
	private static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[N + 2];
		arr[0] = 0;
		arr[N + 1] = L;
		cnt = M;

		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
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