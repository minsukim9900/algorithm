import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static Stack<Integer> stack = new Stack<>();
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		stackSimulate(arr);
	}
	
	
	private static void stackSimulate(int[] arr) {
		int idx = 0;
		
		for (int i = 1; i <= N; i++) {
			stack.push(i);
			sb.append("+").append("\n");

			while (!stack.isEmpty() && stack.peek() == arr[idx]) {
				stack.pop();
				sb.append("-").append("\n");
				idx++;
			}
		}
		
		if(idx == N) {
			System.out.println(sb.toString());
		}else {
			System.out.println("NO");
		}
	}
}