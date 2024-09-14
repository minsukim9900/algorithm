import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int temp = N;
		int a, b;
		int count = 0;
		int c = 0;
		if ( N == 0) {
			count++;
		}
		
		while (c != N) {
			if (temp >= 0 && temp <= 9) {
				a = 0;
				b = temp;
			}
			else {
				a = temp / 10;
				b = temp % 10;
			}

			c = (b * 10) + ((a + b) % 10);
			temp = c;
			count++;
		}
		System.out.println(count);
	}
}
