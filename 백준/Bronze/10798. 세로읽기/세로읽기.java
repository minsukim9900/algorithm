import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] word = new char[5][];
		for(int i=0; i<5;i++) {
			String s = br.readLine();
			word[i] = new char[s.length()];
			for(int j=0;j<s.length();j++) {
				word[i][j] = s.charAt(j);
			}
		}
		
		int max = 0;
		for(int i =0; i<5;i++) {
			if(max<word[i].length) {
				max = word[i].length;
			}
		}
		
		for(int i =0; i<max; i++) {  // 열
			for(int k = 0; k<word.length; k++) {  // 헹
				if(i >= word[k].length) {
					continue;
				}
				System.out.print(word[k][i]);
			}
		}		
	}
}