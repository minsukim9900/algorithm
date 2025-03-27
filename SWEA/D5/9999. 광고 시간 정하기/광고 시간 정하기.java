import java.io.*;
import java.util.*;

public class Solution {
	private static ArrayList<int[]> arr;
	private static int[] sum;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int L = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			sum = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				arr.add(new int[] { s, e });

				if (i == 0) {
					sum[i] = e - s;
				} else {
					sum[i] = sum[i - 1] + (e - s);
				}
			}
			sb.append("#" + t  + " " + simulate(L) + "\n");
		}
		System.out.print(sb.toString());
	}

	private static int simulate(int L) {
		int ans = -1;

		for (int i = 0; i < N; i++) {
			int target = arr.get(i)[0] + L; // i번 광고 시작부터 L시간까지 틀면 (arr[i][0] + L) 까지 광고
			int ub = upperBound(target); // target보다 늦게 끝나는 첫 번째 광고
			int tmp = sum[ub - 1]; // 광고 나가는 시간

			if (i != 0) {
				tmp -= sum[i - 1];
			}

			if (ub != N && target > arr.get(ub)[0]) {
				tmp += (target - arr.get(ub)[0]);
			}

			ans = Math.max(ans, tmp);
		}
		return ans;
	}

	private static int upperBound(int v) {
		int s = 0;
		int e = N - 1;
		int ans = N;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (arr.get(mid)[1] > v) {
				ans = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return ans;

	}
}