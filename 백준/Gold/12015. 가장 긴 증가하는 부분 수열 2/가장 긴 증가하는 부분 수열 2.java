import java.util.*;
import java.io.*;

public class Main {
    
    private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
    
	private static int N;
	private static int[] arr, lis;

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = read();

		arr = new int[N];
		lis = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = read();
		}

		lis[0] = arr[0];

		int idx = 1;

		for (int i = 1; i < N; i++) {
			if (lis[idx - 1] < arr[i]) {

				lis[idx++] = arr[i];

			} else {

				int sIdx = binarySearch(0, idx - 1, arr[i]);
				lis[sIdx] = arr[i];

			}
		}

		System.out.println(idx);

	}

	private static int binarySearch(int left, int right, int target) {

		int mid = 0;

		while (left < right) {

			mid = (left >> 1) + (right >> 1);

			if (lis[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}

		}

		return right;
	}

}