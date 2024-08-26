import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for (int i = N; i > 0; i--) {
			for (int j = N; j>i; j--) {
				sb.append(" ");
			}
			for(int j = N; j>N-i; j--) {
				sb.append("*");
			}
			
			sb.append("\n");
			
		}
		bw.write(sb.toString());
		bw.close();
	}
		
}