import java.io.*;
import java.util.*;

public class Main {



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		double trim = N * 15 / 100.0;
		long tmp = Math.round(trim);
		int[] nums = new int[N];
		
		for(int i = 0; i<N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(nums);
		
		double sum = 0;
		for(int i = (int) tmp; i<N-tmp; i++ ) {
			sum+=nums[i];
		}
		long result = Math.round(sum / (N-tmp*2));
		System.out.println(result);
	}
}