import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int[] nums = new int [10];
		String s = String.valueOf(A*B*C);
		
		for(int i = 0; i<10; i++) {
			for(int j =0; j<s.length(); j++) {
				if(i == s.charAt(j)-'0') {
					nums[i]++;
				}
			}
		}
		
		for(int i = 0; i<nums.length; i++) {
			System.out.println(nums[i]);
		}
		
	}
}
