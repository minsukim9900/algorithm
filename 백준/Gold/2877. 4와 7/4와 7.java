import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int K = Integer.parseInt(br.readLine()) + 1;

		List<Integer> nums = new ArrayList<>();
		while (K > 1) {
			nums.add(K % 2);
			K /= 2;
		}

		for (int i = nums.size() - 1; i >= 0; i--) {
			sb.append(nums.get(i) == 1 ? 7 : 4);
		}
		System.out.println(sb.toString());
	}
}