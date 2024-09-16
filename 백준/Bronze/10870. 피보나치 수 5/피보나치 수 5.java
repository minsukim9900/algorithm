import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int sum = 0;
		int a = 0;
		int b = 1;
		if(N == 1) {
			System.out.println(sum+1);
		}else {
			for (int i = 1; i < N; i++) {
				sum = a + b;
				a = b;
				b = sum;
			}
			System.out.println(sum);
		}
	}
}
