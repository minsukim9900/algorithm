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
		
		for (int i = 1; i <= N; i++) {
			for (int j = N; j>i; j--) {
				sb.append(" ");
			}
			for(int j = 0; j<2*i-1; j++) {
				sb.append("*");
			}
			
			sb.append("\n");
			
		}
		bw.write(sb.toString());
		bw.close();
	}
		
}