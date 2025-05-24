import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int[] arrive = new int[N];
			int max = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arrive[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arrive[i]);
			}

			Arrays.sort(arrive);
			
			String answer = "Possible";
			if(!simulate(max, arrive)) {
				answer = "Impossible";
			}
			sb.append("#" + t + " " + answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean simulate(int max, int[] arrive) {
		int cnt = 0;
		int idx = 0;

		for (int i = 0; i <= max; i++) {
			if (i >= M && i % M == 0) {
				cnt += K;
			}
			
			cnt -= cntPerson(i, arrive);
			if(cnt < 0) {
				return false;
			}
		}
		return true;
	}
	
	private static int cntPerson(int time, int[] arrive) {
		int cnt = 0;
		
		for(int i = 0; i < arrive.length; i++) {
			if(time == arrive[i]) {
				cnt++;
			}
		}
		return cnt;
	}
}