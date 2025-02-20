import java.io.*;
import java.util.*;

public class Solution {

	private static int N;
	private static int[] building;
	private static int sum;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {

			N = Integer.parseInt(br.readLine());
			building = new int[N];
			sum = 0;

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				building[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 2; i < N - 2; i++) {
				isSee(i);
			}

			sb.append("#" + t).append(" ").append(sum+"\n");

		}
		
		System.out.println(sb.toString());

	}

	private static boolean isSee(int idx) {

		int max = 0;

		for (int i = 2; i > 0; i--) {

			if ((building[idx] - building[idx - i]) < 1)
				return false;

			if ((building[idx] - building[idx + i]) < 1)
				return false;

			max = Math.max(max, Math.max(building[idx - i], building[idx + i]));
		}

		sum += (building[idx] - max);

		return true;
	}

}
