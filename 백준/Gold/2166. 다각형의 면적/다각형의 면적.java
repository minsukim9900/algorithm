import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static long[][] nums;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		nums = new long[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nums[i][0] = Long.parseLong(st.nextToken());
			nums[i][1] = Long.parseLong(st.nextToken());
		}
		
		System.out.printf("%.1f\n", cal());
		
	}
	
	private static double cal() {
		long numA = 0;
		long numB = 0;
		
		for(int i = 0; i < N; i++) {
			numA += nums[i][0] * nums[(i + 1) % N][1];
			numB += nums[i][1] * nums[(i + 1) % N][0];
		}		
		return Math.abs(numA - numB) / 2.0;
	}
}
