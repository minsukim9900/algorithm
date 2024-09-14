import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int result = 1;
		
		
		for(int i = N; i>0; i--) {
			if(N==0) {
				break;
			}
			result *= i;
		}
		
		System.out.println(result);
	}
}
