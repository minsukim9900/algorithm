import java.io.*;
import java.util.*;

public class Main {
	private static int N, H;
	private static int[][] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		nums = new int[2][N / 2];

		for (int i = 0; i < N; i++) {
			int idx = 0;
			if (i % 2 == 1) {
				idx = 1;
			}
			nums[idx][i / 2] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < 2; i++) {
			Arrays.sort(nums[i]);
		}

		int min = Integer.MAX_VALUE;
		int count = 0;

		for (int i = 1; i <= H; i++) {
			int conflict = binarySearch(i, 0) + binarySearch(H - i + 1, 1);
			if (min == conflict) {
				count++;
			} else if (min > conflict) {
				count = 1;
				min = conflict;
			}
		}

		System.out.println(min + " " + count);
	}

	private static int binarySearch(int h, int idx) {
		int answer = 0;
		int s = 0;
		int e = N / 2 - 1;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (nums[idx][mid] >= h) {
				answer = (N / 2) - mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return answer;
	}
}