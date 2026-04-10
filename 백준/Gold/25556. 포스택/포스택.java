import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[4];

		int idx = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int currNum = Integer.parseInt(st.nextToken());
			boolean isPoss = false;

			for (int index = 0; index < 4; index++) {
				if (nums[index] < currNum) {
					nums[index] = currNum;
					idx = index;
					isPoss = true;
					break;
				}
			}

			if (!isPoss) {
				System.out.println("NO");
				return;
			}
		}

		System.out.println("YES");
	}
}