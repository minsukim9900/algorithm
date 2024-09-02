import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();

		int totaldial [] = new int[s.length()];
		
		
		int dial [] = new int[10];
		dial[0] = 11;
		dial[1] = 2;
		for(int i = 1; i<9; i++) {
			dial[i+1] = dial[i]+1;
		}
		
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i)=='A' || s.charAt(i) == 'B' || s.charAt(i)=='C') {
				totaldial[i]=2;
				}
			if(s.charAt(i)=='D' || s.charAt(i) == 'E' || s.charAt(i)=='F') {
				totaldial[i]=3;
				}
			if(s.charAt(i)=='G' || s.charAt(i) == 'H' || s.charAt(i)=='I') {
				totaldial[i]=4;
				}
			if(s.charAt(i)=='J' || s.charAt(i) == 'K' || s.charAt(i)=='L') {
				totaldial[i]=5;
				}
			if(s.charAt(i)=='M' || s.charAt(i) == 'N' || s.charAt(i)=='O') {
				totaldial[i]=6;
				}
			if(s.charAt(i)=='P' || s.charAt(i) == 'Q' || s.charAt(i)=='R' || s.charAt(i) == 'S') {
				totaldial[i]=7;
				}
			if(s.charAt(i)=='T' || s.charAt(i) == 'U' || s.charAt(i)=='V') {
				totaldial[i]=8;
				}
			if(s.charAt(i)=='W' || s.charAt(i) == 'X' || s.charAt(i)=='Y' || s.charAt(i)=='Z') {
				totaldial[i]=9;
				}
			
			
			}
		
		int sum = 0;
		
		for(int i = 0; i<s.length(); i++) {
			sum += dial[totaldial[i]];
		}
		System.out.println(sum);
	}
}