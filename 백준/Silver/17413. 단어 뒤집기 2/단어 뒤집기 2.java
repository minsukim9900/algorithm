import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Stack<Character> stack = new Stack<>();

		String str = br.readLine();
		
		boolean turn = false;
		
		for(int i = 0; i<str.length();i++) {
			if(str.charAt(i)=='<') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				turn = true;
				sb.append(str.charAt(i));
			}
			else if(str.charAt(i)=='>') {
				turn = false;
				sb.append(str.charAt(i));
			}
			else if(turn) {
				sb.append(str.charAt(i));
			}
			else if(!turn) {
				if(str.charAt(i)==' ') {
					while(!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					sb.append(str.charAt(i));
				}
				else {
					stack.push(str.charAt(i));
				}
				
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		


        System.out.println(sb.toString());

	}
}