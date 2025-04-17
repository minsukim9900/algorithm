import java.io.*;
import java.util.*;

public class Main {
	private static String A;
	private static boolean isPoss;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		A = br.readLine();
		sb.append(br.readLine());
		dfs(sb);
		
		if(isPoss) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}

	private static void dfs(StringBuilder sb) {

		if (sb.length() == A.length()) {
			
			if (!isPoss && sb.toString().equals(A)) {
				isPoss = true;
			}
			return;
		}
		char tmp = sb.toString().charAt(sb.length() - 1);
		
		if(tmp == 'A') {
			sb.deleteCharAt(sb.length() - 1);
			dfs(sb);
			sb.append(tmp);
		}
		
		tmp = sb.toString().charAt(sb.length() - 1);
		
		if(tmp == 'B') {
			sb.deleteCharAt(sb.length() - 1);
			sb.reverse();
			
			dfs(sb);
			
			sb.reverse();
			sb.append(tmp);
		}
	}
}