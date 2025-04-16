import java.io.*;
import java.util.*;

public class Main {
	private static long N;
	private static int[] dice = new int[6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Long.parseLong(br.readLine());
		
		int min = 51;
		int max = 0;
		int sum = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			sum += dice[i];
			min = Math.min(min, dice[i]);
			max = Math.max(max, dice[i]);
		}
		
		if(N == 1) {
			System.out.println(sum - max);
			return;
		}

		int[] select = selectSide();
		long threeSide = 4 * saveThreeSideNum(select);
		long twoSide = ((N - 2) * 8 + 4) * saveTwoSideNum(select);
		long ownSide = ((N - 2) * (N - 2) * 5 + (N - 2) * 4) * min;
		long rs = threeSide + twoSide + ownSide;
		System.out.println(rs);

	}

	private static int[] selectSide() {
		int[] tmp = new int[3];

		for (int i = 0; i < 3; i++) {
			tmp[i] = Math.min(dice[i], dice[5 - i]);
		}

		return tmp;
	}

	private static int saveTwoSideNum(int[] select) {

		Arrays.sort(select);
		int min = select[0] + select[1];

		return min;
	}

	private static int saveThreeSideNum(int[] select) {
		int sum = 0;

		for (int w : select) {
			sum += w;
		}
		return sum;
	}

}