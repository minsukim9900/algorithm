import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int left = 0;
		int right = left + X - 1;
		int cnt = 0;

		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		long sum = 0;
		long max = 0;
		int count = 1;

		for (int i = 0; i < X; i++) {
			sum += nums[i];
		}

		while (right != N) {
			if (max == sum) {
				count++;
			}
			
			if(max < sum) {
				count = 1;
				max = sum;
			}
			
			if (right + 1 == N) {
				break;
			}
			
			sum -= nums[left++];
			sum += nums[++right];
		}
		if(max == 0) {
			System.out.println("SAD");
			return;
		}
		
		System.out.println(max);
		System.out.println(count);
	}
}