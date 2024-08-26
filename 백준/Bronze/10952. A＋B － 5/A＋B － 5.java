import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int num = 1;
		while(num != 0) {
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		if (k+m>0) {
		System.out.println(k+m);
		}
		else {
			num = 0;
		}
		
		
		}
	}
		
}