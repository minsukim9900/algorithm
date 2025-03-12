import java.io.*;
import java.util.*;

public class Main {

	private static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		ArrayList<String> info = new ArrayList<>();

		int T = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String comp = str;
		int num = 0;
		int t = 0;
		info.add(str);
		while(true) {

			sb = new StringBuilder();

			for (int i = 0; i < str.length(); i++) {
				if(i % 2 == 0) sb.append(str.charAt(i));
				else stack.add(str.charAt(i));
			}
			
			
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			
			str = sb.toString();
			t++;
			if(str.equals(comp)) break;
			info.add(str);
		}
		
		System.out.println(info.get(T % t));

	}
}
