import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String nums = br.readLine();
		int[] set = new int[10];
		
		for(int i = 0; i<nums.length(); i++ ) {
			int a = nums.charAt(i)-'0';
			//a가 9이고 6번방의 배열이 9번방보다 작을 때 6번방에 넣기
			if(a == 9 && set[6]<set[9]) {
				set[6]++;
			}
			else if(a==6 && set[9]<set[6]) {
				set[9]++;
			}
			else {
				set[a]++;
			}
		}
		
		int max = 0;
		for(int i = 0; i<set.length; i++) {
			if(max<set[i]) {
				max = set[i];
			}
		}
		
		System.out.println(max);
		
		
	}
}