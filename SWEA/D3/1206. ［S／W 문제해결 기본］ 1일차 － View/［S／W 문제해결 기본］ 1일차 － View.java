import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] heights = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			
			for (int i = 2; i < N - 1; i++) {
				ans += cntView(i, heights);
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static int cntView(int point, int[] heights) {
		int max = 0;
		
		for(int i = point - 2; i < point + 3; i++) {
			if(i == point) continue;
			max = Math.max(max, heights[i]);
			
			if(heights[i] >= heights[point]) {
				return 0;
			}
		}
		return Math.abs(heights[point] - max);
	}
}