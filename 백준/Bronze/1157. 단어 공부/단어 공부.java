import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String word = br.readLine();
		int Alphabet[] = new int [26];
		
		for(int i=0; i<word.length(); i++) {
			if(word.charAt(i)<=90 && word.charAt(i)>=65 ) {
				int A = word.charAt(i)-'A';
				Alphabet[A]++;
			}
			else {
				int a = word.charAt(i)-'a';
				Alphabet[a]++;
			}
		}
		int max = 0;
		for(int i=0; i<26; i++) {
			if(max<Alphabet[i]) {
				max = Alphabet[i];
			}
		}
		
		int cnt = 0;
		int value = 0;
		for(int i=0; i<26; i++) {
			if(max == Alphabet[i]) {
				cnt++;
				value = i;
			}
		}
		if(cnt>=2) {
			System.out.println("?");
		}
		else if(cnt==1) {
			System.out.println((char)(value + 'A'));
		}
		
	}
}