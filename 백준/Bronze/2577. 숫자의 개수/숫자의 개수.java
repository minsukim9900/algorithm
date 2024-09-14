import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());

		int result = A * B * C;

		int[] nums = new int[10];

		while (result != 0) {
			nums[result % 10]++;
			result /= 10;
		}

		for (int i = 0; i<nums.length; i++) {
			sb.append(nums[i]).append("\n");
		}

		System.out.println(sb.toString());

	}
}
