import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		String N = st.nextToken();
		int[] number = new int[N.length()];
		int B = Integer.parseInt(st.nextToken());
		int[] C = new int [N.length()];
		
		for(int i = 0; i<N.length(); i++) {
			if(N.charAt(i) >= 'A' && N.charAt(i) <= 'Z') {
				number[i] = N.charAt(i)-55;
			}
			else {
				number[i] = N.charAt(i)-'0';
			}
		}
		
		int square = 1;
		
		for(int i = N.length(); i>0; i--) {
			if(i == N.length()) {
				C[i-1] = 1;
			}
			else{
				square *= B;
				C[i-1] = square;
			}
		}
		
		int sum = 0;
		
		for(int i = 0; i<N.length(); i++) {
				sum += number[i]*C[i];
		}
		System.out.println(sum);
	}
}