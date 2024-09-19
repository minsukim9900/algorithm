import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i<num; i++) {
			int a = Integer.parseInt(br.readLine());
			
			if(a == 0) {
				stack.pop();
			}
			else {
				stack.push(a);
			}
		}
		
		int sum =0;
		
		for(int i:stack) {
			sum += i;
		}
		System.out.println(sum);
	}
}