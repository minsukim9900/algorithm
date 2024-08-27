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
		
		for(int i = 0; i<N; i++) {
			for (int j=1; j<=i; j++) {
				sb.append(" ");
			}
			for (int j=2*N-1; j>2*i; j--) {
				sb.append("*");
			}
			sb.append("\n");
		}
		for(int i = 0; i<N-1; i++) {
			for(int j=N-2; j>i; j--) {
				sb.append(" ");
			}
			for(int j=0; j<2*i+3; j++) {
				sb.append("*");
			}
			
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
	}
		
}