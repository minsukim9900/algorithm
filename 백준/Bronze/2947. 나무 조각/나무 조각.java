import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] nums = new int[5];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int j = 0;
		while (true) {
			if (nums[j] == 1 && nums[j + 1] == 2 && nums[j + 2] == 3 && nums[j + 3] == 4 && nums[j + 4] == 5) {
				break;
			}
			else {
				
				int i = 0;
				while(i<4) {
					if(nums[i] > nums[i+1]) {
						int tmp = nums[i];
						nums[i] = nums[i+1];
						nums[i+1] = tmp;
						for(int k = 0; k<nums.length; k++) {
							sb.append(nums[k]).append(" ");
						}
						sb.append("\n");
					}
					i++;
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}