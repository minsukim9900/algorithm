import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String parenthesis = br.readLine();
			Stack<Character> stack = new Stack<>();
			for (int w = 0; w < parenthesis.length(); w++) {
				if (parenthesis.charAt(w) ==  '(') {
					stack.push(parenthesis.charAt(w));
				} else{
					if(stack.empty()) {
						stack.push(parenthesis.charAt(w));
						break;
					}
					else {
						stack.pop();
					}
				}
					
			}
			if (stack.empty()) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
}