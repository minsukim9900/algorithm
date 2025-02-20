import java.io.*;
import java.util.*;

public class Solution {

	private static int[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {

			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			nums = new int[100];
			for (int i = 0; i < 100; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < N; i++) {
				Arrays.sort(nums);

				nums[0]++;
				nums[99]--;
			}
				
			Arrays.sort(nums);
			
			sb.append("#"+t+" ").append(nums[99]-nums[0]).append("\n");
		}
		
		
		System.out.println(sb.toString());
	}

}
