import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int find = Integer.parseInt(st.nextToken());
			sb.append(search(find)).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static int search(int find) {
		int left = 0;
		int right = nums.length - 1;

		if(nums[left] > find || nums[right] < find) {
			return 0;
		}else {
			while (left <= right) {
				
				
				int mid = (left + right) / 2;
				
				if (nums[mid] == find) {
					return 1;
				} else if (nums[mid] > find) {
					right = mid - 1;
				}else {
					left = mid + 1;
				}
				
				
			}
		}
		

		return 0;

	}

}