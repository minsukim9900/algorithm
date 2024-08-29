import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N= Integer.parseInt(br.readLine());
		String x = br.readLine();
		char y [] = new char [N];
		int sum = 0;
		for(int i=0; i<N; i++) {
			y[i] = x.charAt(i);
			sum += Character.getNumericValue(y[i]);
			
		}
		System.out.println(sum);
	}
}