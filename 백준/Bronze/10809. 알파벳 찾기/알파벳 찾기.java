import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int Alphabet [] = new int[26];
		for(int i = 0; i<Alphabet.length; i++) {
			Alphabet[i] = -1;
		}
		
		String s = br.readLine();
	
		for(int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			
			if(Alphabet[ch-'a']== -1) {
				Alphabet[ch-'a'] = i;
			}
		}
		
		for(int i=0; i<Alphabet.length; i++) {
			System.out.print(Alphabet[i]+" ");
		}
		
		
		
	}
}