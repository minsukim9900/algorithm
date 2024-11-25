import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] nums;
	private static int[] result;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N+1];
		
		for(int i = 0; i< N; i++) {
			nums[i] = i+1;
		}
		result = new int[M];
		
		combination(0, 0);
		System.out.println(sb.toString());
	}
	
	public static void combination(int idx, int sidx) {
		
		if(sidx == M) {
			for(int i = 0; i<M; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = idx; i<=N-M+sidx; i++) {
			result[sidx] = nums[i];
			combination(i+1, sidx+1);
		}
		
	}

}
