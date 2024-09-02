import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String sentence = br.readLine().trim();
		String result [] = sentence.split(" ");
		if ( sentence.equals("")) {
			System.out.println(0);
			}
		else {
			System.out.println(result.length);
		}
		}
}