import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int sum = 1;
		int left = 1;
		int right = 1;
		int count = 0;

		while (left <= N) {
			if (sum == N) {
				count++;
				sum -= left++;
			} else if (sum < N) {
				right++;
				if (right > N)
					break;
				sum += right;
			} else {
				sum -= left++;
			}
		}

		System.out.println(count);
	}
}