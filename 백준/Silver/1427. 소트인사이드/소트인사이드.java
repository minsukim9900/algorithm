import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		char[] ch = br.readLine().toCharArray();
		Arrays.sort(ch);
		
		for(int i = ch.length - 1; i >= 0; i--) {
			sb.append(ch[i]);
		}
		System.out.println(sb.toString());
	}
}
