import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine())+1;
		
		int temp1 = 1;
		int temp2 = 1;
		
		for(int i = 1; i<N; i++) {
			temp1 *= 4;
		}
		
		for(int i =1; i<N; i++) {
			temp2 *= 2;
		}
		
		int c = temp1/temp2 + 1 ; 
		System.out.println(c * c);
	}
}