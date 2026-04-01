import java.io.*;
import java.util.*;

public class Main {
	private static int N, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		int count = 1;
		int end = nums[0] + L;

		for (int i = 1; i < N; i++) {
			if (end > nums[i])
				continue;

			end = nums[i] + L;
			count++;
		}
		
		System.out.println(count);
	}
}