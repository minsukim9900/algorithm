import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int count = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			if (s.length()% 2 ==1) {
				count+=0;
			}
			else {
				Stack<Character> stack = new Stack<>();
				stack.push(s.charAt(0));
				for(int c = 1; c<s.length(); c++) {
					if(stack.size()>0 && stack.peek() == s.charAt(c)) {
						stack.pop();
					}else {
						stack.push(s.charAt(c));
					}
				}
				if(stack.isEmpty()) count++;
			}
			
		}
		System.out.println(count);
	}
}