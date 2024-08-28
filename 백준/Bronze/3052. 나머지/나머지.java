import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int remain[] = new int [42];
		for(int i=0; i<10; i++) {
			int x = Integer.parseInt(br.readLine());
			int y = x % 42;
			remain[y] = 1;
		}
		int cnt = 0;
		
		for(int i=0; i<remain.length; i++) {
			if(remain[i] == 1) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}