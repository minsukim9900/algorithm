import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		int[] count = new int[10001];
		for(int i = 1; i<=10000; i++) {
			int n = d(i);
			
			if(n<10001) {
				count[n]=1;
			}
			if(count[i]==0) {
				sb.append(i).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	public static int d(int num) {
		int sum = num;
		
		while(num != 0) {
			sum += num % 10;
			num /= 10;
		}
		
		return sum;
	}
}