import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nums);

		int B = 1;
		long ans = 0;
		for (int i = 0; i < N; i++) {
			ans += Math.abs(nums[i] - B++);
		}
		System.out.println(ans);
	}
}
