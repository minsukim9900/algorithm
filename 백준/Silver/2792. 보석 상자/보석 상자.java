import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, max;
	private static int[] nums;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[M];
		for(int i = 0; i < M; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, nums[i]);
		}
		System.out.println(binarySearch());
	}
	
	private static int binarySearch() {
		int answer = 0;
		int s = 1;
		int e = max;
		
		while(s <= e) {
			int mid = (s + e) / 2;
			
			if(check(mid)) {
				answer = mid;
				e = mid - 1;
			}else {
				s = mid + 1;
			}
		}
		return answer;
	}
	
	private static boolean check(int v) {
		int cnt = 0;
		for(int w : nums) {
			cnt += w / v;
			
			if(w % v != 0) {
				cnt++;
			}
		}
		
		return cnt <= N;
	}
}
