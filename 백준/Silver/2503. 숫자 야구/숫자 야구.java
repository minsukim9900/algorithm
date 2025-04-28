import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static String[] nums;
	private static int[] strikeInfo, ballInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new String[N];
		strikeInfo = new int[N];
		ballInfo = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nums[i] = st.nextToken();
			strikeInfo[i] = Integer.parseInt(st.nextToken());
			ballInfo[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int cnt = 0;

		for (int i = 1; i <= 9; i++) {

			for (int j = 1; j <= 9; j++) {
				if (i == j) {
					continue;
				}

				for (int k = 1; k <= 9; k++) {
					if (i == k || k == j) {
						continue;
					}

					if (isPoss(i, j, k)) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

	private static boolean isPoss(int x, int y, int z) {
		
		for (int i = 0; i < N; i++) {
			int strike = 0;
			int ball = 0;
			int compX = nums[i].charAt(0) - '0';
			int compY = nums[i].charAt(1) - '0';
			int compZ = nums[i].charAt(2) - '0';
			
			strike += count(x, compX);
			ball += count(x, compY);
			ball += count(x, compZ);
			
			strike += count(y, compY);
			ball += count(y, compX);
			ball += count(y, compZ);
			
			strike += count(z, compZ);
			ball += count(z, compY);
			ball += count(z, compX);
			
			if(strike != strikeInfo[i] || ball != ballInfo[i]) {
				return false;
			}
		}
		return true;
	}
	
	private static int count(int x, int compX) {
		return x == compX ? 1 : 0;
	}
	
}