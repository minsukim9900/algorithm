import java.io.*;
import java.util.*;

public class Solution {

	private static int[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			nums = new int[10001];
			int test = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 1000; i++) {
				nums[Integer.parseInt(st.nextToken())]++;
			}

			int max = 0;
			int maxIdx = 0;

			for (int i = 1000; i >= 0; i--) {
				if (max < nums[i]) {
					max = nums[i];
					maxIdx = i;
				}
			}

			sb.append("#" + t + " ").append(maxIdx+"\n");

		}
		
		System.out.println(sb.toString());

	}

}
