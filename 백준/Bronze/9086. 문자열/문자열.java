import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringBuilder sb = new StringBuilder();
			String a = br.readLine();
			sb.append(a.charAt(0)).append(a.charAt(a.length()-1));
			System.out.println(sb.toString());
		}
	}
}