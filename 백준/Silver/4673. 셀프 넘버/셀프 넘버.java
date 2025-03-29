import java.io.*;
import java.util.*;

public class Main {
	private static boolean[] visited = new boolean[10001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int i = 1; i <= 10000; i++) {
			if (!visited[i]) {
				simulate(i);
				sb.append(i + "\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void simulate(int num) {
		if (num > 10000)
			return;
		visited[num] = true;
		int selfNum = 0;
		simulate(makeSelfNum(num));
	}

	private static int makeSelfNum(int num) {
		int sum = num;
		String nums = String.valueOf(num);

		for (int i = 0; i < nums.length(); i++) {
			sum += (nums.charAt(i) - '0');
		}

		return sum;
	}
}