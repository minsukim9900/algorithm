import java.io.*;
import java.util.*;

public class Solution {
	private static int N, P;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sb.append("#"  + t + " " + binarySearch() + "\n"); 
		}
		System.out.println(sb.toString());
	}

	private static int binarySearch() {
		int ans = 0;

		for (int i = 0; i < N; i++) {
			int s = i;
			int e = N;

			while (s != e) {
				int mid = s + (e - s) / 2;
				int blank = (arr[mid] - arr[i] + 1) - (mid - i + 1);

				if (blank <= P) {
					ans = Math.max(ans, (arr[mid] - arr[i] + 1) + (P - blank));
					s = mid + 1;
				} else {
					e = mid;
				}
			}
		}
		return ans;
	}

}