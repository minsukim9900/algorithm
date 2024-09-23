import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String alphabet = br.readLine();
		for (int i = 0; i < alphabet.length(); i++) {
			if (alphabet.charAt(i) <= 'z' && alphabet.charAt(i) >= 'n') {
				int a = alphabet.charAt(i) - 13;
				System.out.print((char) a);
			} else if (alphabet.charAt(i) < 'n' && alphabet.charAt(i) >= 'a') {
				int b = alphabet.charAt(i) + 13;
				System.out.print((char) b);
			} else if (alphabet.charAt(i) <= 'Z' && alphabet.charAt(i) >= 'N') {
				int a = alphabet.charAt(i) - 13;
				System.out.print((char) a);
			} else if (alphabet.charAt(i) < 'N' && alphabet.charAt(i) >= 'A') {
				int b = alphabet.charAt(i) + 13;
				System.out.print((char) b);
			} else {
				System.out.print(alphabet.charAt(i));
			}
		}
	}
}