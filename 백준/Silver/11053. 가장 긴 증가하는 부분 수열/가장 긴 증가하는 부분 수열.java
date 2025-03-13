import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[] arr, lis;

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

		lis = new int[N];

		lis[0] = arr[0];
		int idx = 1;

		for (int i = 1; i < N; i++) {
			if (lis[idx - 1] < arr[i]) {
				lis[idx++] = arr[i];
			} else {
				int sidx = binarySearch(0, idx - 1, arr[i]);
				lis[sidx] = arr[i];
			}

		}
		
		System.out.println(idx);
		
	}

	private static int binarySearch(int left, int right, int target) {

		int mid = 0;

		while (left < right) {
			mid = (left >> 1) + (right >> 1);

			if (target > lis[mid]) {
				left = mid + 1;
			} else {
				right = mid;
			}

		}

		return right;

	}

}
