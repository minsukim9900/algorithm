import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		String str = br.readLine();
		System.out.println(cal(str));
	}

	/*
	 * ‘()’ 인 괄호열의 값은 2이다. ‘[]’ 인 괄호열의 값은 3이다. ‘(X)’ 의 괄호값은 2×값(X) 으로 계산된다. ‘[X]’ 의
	 * 괄호값은 3×값(X) 으로 계산된다. 바른 괄호열 X와 Y가 결합된 XY의 괄호값은 값(XY)= 값(X)+값(Y) 로 계산된다.
	 */

	private static int cal(String str) {
		int ans = 0;
		int tmp = 1;
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == '(' || ch == '[') {

				if (ch == '(') {
					tmp *= 2;
				} else if (ch == '[') {
					tmp *= 3;
				}

				stack.push(ch);
			} else if (ch == ')') {

				if (stack.isEmpty() || stack.peek() != '(') {
					ans = 0;
					break;
				} else if (str.charAt(i - 1) == '(') {
					ans += tmp;
				}
				
				tmp /= 2;
				stack.pop();

			} else if (ch == ']') {

				if (stack.isEmpty() || stack.peek() != '[') {
					ans = 0;
					break;
				} else if (str.charAt(i - 1) == '[') {
					ans += tmp;
				}
				
				tmp /= 3;
				stack.pop();

			}

		}
		
		if(!stack.isEmpty()) ans = 0;

		return ans;
	}
}