import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] nums = new int[N+1];

		int num = 2;
		int tmp = 0;
		while (tmp != K) {
				for (int i = num; i <= N; i += num) {
					if (nums[i] == 0) {
						nums[i] = ++tmp;
					}
					if(tmp == K) {
						break;
					}
				}

			num++;
		}

		for (int j = 0; j < nums.length; j++) {
			if (nums[j] == tmp) {
				System.out.println(j);
			}
		}

	}
}