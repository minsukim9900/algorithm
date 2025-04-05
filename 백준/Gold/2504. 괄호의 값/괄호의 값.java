import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Stack<Object> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[') {
                stack.push(ch);
            } else {
                int innerSum = 0;

                while (!stack.isEmpty()) {
                    Object top = stack.pop();

                    if (top instanceof Integer) {
                        innerSum += (int) top;
                    } else if (ch == ')' && top.equals('(')) {
                        stack.push(innerSum == 0 ? 2 : 2 * innerSum);
                        break;
                    } else if (ch == ']' && top.equals('[')) {
                        stack.push(innerSum == 0 ? 3 : 3 * innerSum);
                        break;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }

                if (stack.isEmpty() && (ch == ')' || ch == ']')) {
                    System.out.println(0);
                    return;
                }
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            Object top = stack.pop();
            if (top instanceof Integer) {
                result += (int) top;
            } else {
                System.out.println(0);
                return;
            }
        }

        System.out.println(result);
    }
}