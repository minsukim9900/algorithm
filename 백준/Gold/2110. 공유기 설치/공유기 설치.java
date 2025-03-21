import java.io.*;
import java.util.*;

public class Main {
	private static int N, C;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int s = 1;
		int e = Integer.MAX_VALUE;
		int result = 0;
		while (s <= e) {

			int mid = (s >> 1) + (e >> 1);

			int cnt = check(mid);
			if (cnt >= C) {
				result = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return result;
	}

	private static int check(int v) {
		int cnt = 1;

		for (int i = 0; i < N - 1; i++) {
			int j = i + 1;

			while (j < N) {
				if (j >= N)
					break;

				if (arr[j] >= arr[i] + v) {
					cnt++;
					break;
				}
				j++;
			}
			i = j - 1;
		}

		
		return cnt;
	}
}