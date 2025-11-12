import java.io.*;
import java.util.*;

public class Main {
	private static int[] arr, result;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N];
			result = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr);
			int left = 0;
			int right = N - 1;
			
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0) {
					result[left++] = arr[i];
				} else {
					result[right--] = arr[i];
				}
			}

			int answer = Math.abs(result[N - 1] - result[0]);
			for (int i = 0; i < N - 1; i++) {
				answer = Math.max(answer, Math.abs(result[i] - result[i + 1]));
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}