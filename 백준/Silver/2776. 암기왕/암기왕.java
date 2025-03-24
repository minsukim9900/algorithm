import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			N = Integer.parseInt(br.readLine());
			memo = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				memo[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(memo);

			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < M; i++) {
				sb.append(binarySearch(Integer.parseInt(st.nextToken())) + "\n");
			}
			
		}
		System.out.println(sb.toString());

	}

	private static int binarySearch(int v) {
		int result = 0;

		int s = 0;
		int e = N - 1;

		while (s <= e) {
			int mid = (s + e) >>1;

			if (memo[mid] == v) {
				result = 1;
				return result;
			}

			if (memo[mid] > v) {
				e = mid - 1;
			} else if (memo[mid] < v) {
				s = mid + 1;
			}
		}

		return result;
	}

}