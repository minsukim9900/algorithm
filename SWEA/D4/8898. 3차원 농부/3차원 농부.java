import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M;
	private static int[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int dx = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));

			cows = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cows[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(cows);

			int min = Integer.MAX_VALUE;
			int count = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int hPos = Integer.parseInt(st.nextToken());
				int X = binarySearch(hPos); // 현재 말보다 더 멀리 있으면서 가장 가까운 소의 위치

				
				if (X < cows.length) {
					int dist = cows[X] - hPos;
					if (min == dist) {
						count++;
					} else if (min > dist) {
						min = dist;
						count = 1;
					}
				}

				if (X - 1 >= 0) {
					int dist = hPos - cows[X - 1];
					if (min == dist) {
						count++;
					} else if (min > dist) {
						min = dist;
						count = 1;
					}
				}
			}

			sb.append("#" + t + " " + (dx + min) + " " + count + "\n");
		}
		System.out.println(sb.toString());
	}

	private static int binarySearch(int value) {
		int s = 0;
		int e = cows.length - 1;
		int result = cows.length;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (cows[mid] >= value) {
				result = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}

		return result;
	}
}