import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int count = N;
		
		for(int i = 0; i < N; i++) {
			String word = br.readLine();
			
			int prev = 0;;
			int [] Alphabet = new int [26];
			
			for(int j = 0; j < word.length(); j++) {
				
				int now = word.charAt(j);
				
				if(prev != now) {
					if(Alphabet[now-'a']==0) {
						Alphabet[now-'a']++;
						prev = now;
					}
					else {
						count--;
						break;
					}
				}
			}
			
		}
		
		System.out.println(count);
		
	}
		
}
