import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		long number = B;
		long numA = A;
		long numB = B;
		while (numA % numB != 0) {
			number = numA % numB;

			numA = numB;
			numB = number;
		}

		System.out.println((A * B) / number);
	}
}