import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		
		/**
		 * dist 배열 채우기, N개 중 1번 째 입력 값으로 dist 채우기
		 * 
		 */
		st = new StringTokenizer(br.readLine());
		int cost = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int[] dp = new int[C + 1];
		
		for (int c = 0; c <= C; c++) {
			dp[c] = (c / p) * cost;

			if (c % p != 0) {
				dp[c] += cost;
			}
		}
		
		
		/**
		 * N-1개 dp 활용
		 * dp 배열 인덱스는 호텔 x명
		 * 인덱스에 들어갈 값: x명을 유치하기 위한 최소 비용
		 * 비교 값: 이전의 dp[x	], dp[x - p] + cost 중 최솟값
		 * preIdx가 필요한 이유 j - p를 하게 되면 0보다 더 작아질 수 있다.
		 */
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			cost = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());

			for (int c = 1; c <= C; c++) {
				int currCost = (c / p) * cost;

				if (c % p != 0) {
					currCost += cost;
				}
				int preIdx = Math.max(0, c - p);
				dp[c] = Math.min(dp[c], dp[preIdx] + cost);
			}
		}

		System.out.println(dp[C]);
	}
}