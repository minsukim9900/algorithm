import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] H = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}

		List<int[]> arr = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int plus = Integer.parseInt(st.nextToken());
			arr.add(new int[] { H[i], plus });
		}
		arr.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

		long answer = 0L;
		for (int i = 0; i < N; i++) {
			int[] curr = arr.get(i);
			long sum = curr[0] + (curr[1] * i);
			answer += sum;
		}
		System.out.println(answer);
	}
}