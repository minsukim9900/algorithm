import java.io.*;
import java.util.*;

public class Main {

	private static boolean check;
	private static char[] duple;
	private static StringBuilder sb = new StringBuilder();
	private static String str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			str = br.readLine();
			if (str.equals("end")) {
				break;
			} else {
				check(str);
			}
		}
		
		System.out.println(sb.toString());
		

	}

	public static void check(String str) {
		check = false;
		duple = new char[1];
		int cntC = 0; // 자음 갯수
		int cntG = 0; // 모음 갯수
		int n = str.length();
		for (int i = 0; i < n; i++) {
			char c = str.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				check = true;
				cntG++;
				cntC = 0;
			} else {
				cntC++;
				cntG = 0;
			}
			if(duple[0] != c || (duple[0] == 'e' && c =='e') || (duple[0] == 'o' && c =='o')) {
				duple[0] = c;
			}else {
				check = false;
				output(str);
				return;
			}
			
			if(cntC >= 3 || cntG >=3) {
				check = false;
				output(str);
				return;
			}
		}
		output(str);
	}
	
	public static void output(String str) {
		if(check) {
			sb.append("<").append(str).append("> is acceptable.").append("\n");
		}else {
			sb.append("<").append(str).append("> is not acceptable.").append("\n");
		}
	}
}
