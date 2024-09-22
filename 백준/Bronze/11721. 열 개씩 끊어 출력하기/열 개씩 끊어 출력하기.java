import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String word = br.readLine();

		int count1 = 0;
		int fin = (word.length() / 10) * 10;
		String s = "";

		for (int i = 0; i < word.length(); i++) {
			s += word.charAt(i);
			count1++;
			if (count1 == 10) {
				System.out.println(s);
				s = "";
				count1 = 0;
			}
			if(i>=fin ) {
				sb.append(word.charAt(i));
			}
		}
		System.out.println(sb.toString());
	}
}