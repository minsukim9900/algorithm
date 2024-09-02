import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		char p [] = new char [s.length()];
		
		for(int i=0; i<p.length; i++) {
			p[i] = s.charAt(i);
		}
		
		int a = 0;
		int b = s.length()-1;
		int result = 0;
		
		while(a<=b) {
			if(p[a]==p[b]) {
				result=1;
				a++;
				b--;
			}
			else {
				result=0;
				break;
			}
		}
		
		System.out.println(result);
	}
}