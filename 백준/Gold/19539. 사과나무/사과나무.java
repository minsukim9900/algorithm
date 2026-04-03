import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int sum = 0;
		int oneCount = 0;
		int twoCount = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			sum += num;
			oneCount += (num % 2);
			twoCount += (num / 2);
		}
		
		String answer = "YES";
		if(sum % 3 != 0 || oneCount > twoCount) {
			answer = "NO";
		}
		
		System.out.println(answer);
	}
}