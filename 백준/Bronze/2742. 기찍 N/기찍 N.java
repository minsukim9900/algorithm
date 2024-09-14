import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(br.readLine());
		
		for(int i = N; i>0; i--) {
			sb.append(i).append("\n");
		}
		System.out.println(sb.toString());
	}
}
