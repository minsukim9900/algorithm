import java.io.*;
import java.util.*;

public class Main {
	private static int K, min, max;
	private static List<Integer>[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init();
			String str = br.readLine();
			K = Integer.parseInt(br.readLine());

			for (int i = 0; i < str.length(); i++) {
				int index = str.charAt(i) - 'a';
				arr[index].add(i);
			}

			cal();
			if (max == 0) {
				sb.append(-1);
			} else {
				sb.append(min + " " + max);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void cal() {
		for (int i = 0; i < 26; i++) {
			if (arr[i].size() < K)
				continue;
			int left = 0;
			int right = K - 1;

			while (right < arr[i].size()) {
				min = Math.min(min, arr[i].get(right) - arr[i].get(left) + 1);
				max = Math.max(max, arr[i].get(right) - arr[i].get(left) + 1);
				left++;
				right++;
			}
		}
	}

	private static void init() {
		min = Integer.MAX_VALUE;
		max = 0;
		arr = new ArrayList['z' - 'a' + 1];
		for (int i = 0; i < 'z' - 'a' + 1; i++) {
			arr[i] = new ArrayList<>();
		}
	}
}