import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int cnt = 0;

		for (int k = 0; k < N; k++) {
			boolean isGood = false;

			for (int i = 0; i < N; i++) {
				if (k == i)
					continue;
				int target = arr[k] - arr[i];
				int idx = lowerBound(target);

				if (idx < N && arr[idx] == target) {
					if (idx != k && idx != i) {
						isGood = true;
						break;
					} else {
						if (idx - 1 >= 0 && arr[idx - 1] == target && (idx - 1 != k && idx - 1 != i)) {
							isGood = true;
							break;
						}
						if (idx + 1 < N && arr[idx + 1] == target && (idx + 1 != k && idx + 1 != i)) {
							isGood = true;
							break;
						}
					}
				}
			}
			if (isGood) {
				cnt++;
			}
		}

		System.out.println(cnt);

	}

	private static int lowerBound(int target) {
		int s = 0;
		int e = N - 1;
		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (arr[mid] >= target) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return s;
	}
}