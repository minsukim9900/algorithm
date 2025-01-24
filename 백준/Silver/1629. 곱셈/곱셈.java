import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		System.out.println(power(A, B, C));
		
	}

	private static long power(int A, int B, int C) {

		if (B == 1 || B == 0) {
			return (A*B) % C;
		} else {

			long num = power(A, B / 2, C);

			if (B % 2 == 0) {
				return (num * num) % C;
				
			} else {
				return A *( num * num % C) % C;
			}

		}

	}

}