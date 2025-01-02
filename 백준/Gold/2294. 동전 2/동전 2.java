import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] dp = new int[K + 1];
		Arrays.fill(dp, 987654321);
		dp[0] = 0;

		ArrayList<Integer> info = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
				info.add(tmp);
		}

		Collections.sort(info);
		
		for (int i = 0; i < info.size(); i++) {
			for (int j = info.get(i); j <= K; j++) {
				dp[j] = Math.min(dp[j], dp[j-info.get(i)] + 1);
			}
		}
		
		if(dp[K] == 987654321) dp[K] = -1;
		System.out.println(dp[K]);

	}
}