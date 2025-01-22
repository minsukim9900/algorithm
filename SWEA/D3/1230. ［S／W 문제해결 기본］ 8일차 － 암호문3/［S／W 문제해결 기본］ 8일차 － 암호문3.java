import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {

			int N = Integer.parseInt(br.readLine());
			LinkedList<Integer> pw = new LinkedList<>();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				pw.add(Integer.parseInt(st.nextToken()));
			}

			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {

				String opt = st.nextToken();
				
				int idx = 0;
				int cnt = 0;
				int[] nums;
				
				switch (opt) {
				case "I": {
					idx = Integer.parseInt(st.nextToken());
					cnt = Integer.parseInt(st.nextToken());
					nums = new int[cnt];
					for (int i = 0; i < cnt; i++) {
						nums[i] = Integer.parseInt(st.nextToken());
					}
					
					for(int i = nums.length -1; i>=0; i--) {
						pw.add(idx, nums[i]);
					}

					break;
				}
				case "D": {
					idx = Integer.parseInt(st.nextToken());
					cnt = Integer.parseInt(st.nextToken());
					for(int i = 0; i<cnt; i++) {
						pw.remove(idx);
					}
					break;
				}
				case "A": {
					cnt = Integer.parseInt(st.nextToken());
					for(int i = 0; i<cnt; i++) {
						pw.add(Integer.parseInt(st.nextToken()));
					}
					break;
				}

				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for(int i = 0; i<10; i++) {
				sb.append(pw.get(i)).append(" ");
			}
			
			System.out.println(sb.toString());

		}
	}

}
