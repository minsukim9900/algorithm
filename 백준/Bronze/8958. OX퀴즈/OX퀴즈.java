import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String quiz = br.readLine();
			int weight = 1;
			int sum = 0;
			for (int j = 0; j < quiz.length(); j++) {

				if(quiz.charAt(j)=='O') {
					sum+=weight;
					weight++;
				}
				else {
					weight = 1;
				}
			}
			System.out.println(sum);
		}
	}

}