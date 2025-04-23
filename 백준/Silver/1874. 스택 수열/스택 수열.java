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

		int[] comp = stackSimulate(arr);
		
		if(isPossible(arr, comp)) {
			System.out.println(sb.toString());
		}else {
			System.out.println("NO");
		}

	}
	
	private static boolean isPossible(int[] arr, int[] comp) {
		for(int i = 0; i<N; i++) {
			if(arr[i] != comp[i]) {
				return false;
			}
		}
		return true;
	}
	
	private static int[] stackSimulate(int[] arr) {
		int[] comp = new int[N];
		
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			stack.push(i);
			sb.append("+").append("\n");

			while (!stack.isEmpty() && stack.peek() == arr[idx]) {
				comp[idx++] = stack.pop();
				sb.append("-").append("\n");
			}
			
		}

		while (!stack.isEmpty()) {
			comp[idx++] = stack.pop();
			sb.append("-").append("\n");
		}
		
		return comp;
	}
}