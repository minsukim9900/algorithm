import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int pick, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			dfs(1, 0);
			
			sb.append(ans + "\n");
			for (int i = 0; i < N; i++) {
				if ((pick & (1 << i)) != 0) {
					sb.append(i + 1).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void dfs(int num, int select) {
		if(num > N) {
			int cnt = Integer.bitCount(select);
			
			if(cnt > ans) {
				ans = cnt;
				pick = select;
			}
		}else {
			int remain = N - num +1;
			if(Integer.bitCount(select) + remain <= ans) {
				return;
			}
			
			dfs(num + 1, select);
			
			if(isPoss(num, select)) {
				dfs(num + 1, select |= (1 << (num - 1)));
			}
			
		}
	}

	private static boolean isPoss(int x, int select) {
		
		for(int i = 0; i<N; i++) {
			if((select & ( 1 << i)) == 0) continue;
			
			for(int j = i + 1; j<N; j++) {
				if((select & (1 << j)) == 0) continue;
				if(((i + 1) ^ (j + 1)) == x) return false;
			}
		}
		
		return true;
	}

}