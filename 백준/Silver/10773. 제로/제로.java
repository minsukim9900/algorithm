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

		int line = Integer.parseInt(br.readLine());
		Stack<Integer> nums = new Stack<>();

		for (int i = 1; i <= line; i++) {
			int number = Integer.parseInt(br.readLine());
			if (number == 0) {
				nums.pop();
			}
			else if (number != 0) {
				nums.add(number);
			}
		}
		int sum = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);
		}
		System.out.println(sum);
	}
}