import java.io.*;
import java.util.*;

public class Solution {

	private static int N, M;
	private static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			count = new int[N + M + 1];
			
			sb.append("#").append(t).append(" ");
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					int sum = i + j;
					count[sum]++;
				}

			}

			int maxNum = 0;

			for (int c : count) {

				if (maxNum < c) {
					maxNum = c;
				}
			}
			
			int idx = 0;
			int tmp = 0;
			for(int c : count) {
				
				if(maxNum == c) {
					sb.append(tmp).append(" ");
				}
				tmp++;
				
			}
			sb.append("\n");

		}
		System.out.println(sb.toString());

	}

}
