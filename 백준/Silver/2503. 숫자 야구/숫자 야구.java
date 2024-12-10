import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] info;
	private static int count = 0;
	private static boolean[] check = new boolean[988];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		info = new int[N][5];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String Num = st.nextToken();

			int num1 = Num.charAt(0) - '0';
			int num2 = Num.charAt(1) - '0';
			int num3 = Num.charAt(2) - '0';

			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());

			info[i][0] = num1;
			info[i][1] = num2;
			info[i][2] = num3;
			info[i][3] = strike;
			info[i][4] = ball;

		}
		FirstWork();
		
		for (int i = 0; i < N; i++) {

			for (int j = 123; j < 988; j++) {
				if(check[j]) {
					String num = String.valueOf(j);
					int num1 = num.charAt(0) - '0';
					int num2 = num.charAt(1) - '0';
					int num3 = num.charAt(2) - '0';
					comp(i, j, num1, num2, num3);
				}
			}

		}
		
		for(boolean b : check) {
			if(b) count++;
		}
		
		System.out.println(count);
		
	}

	private static void comp(int comp_idx, int Number, int num1, int num2, int num3) {
		int comp1 = info[comp_idx][0];
		int comp2 = info[comp_idx][1];
		int comp3 = info[comp_idx][2];
		int compStrike = info[comp_idx][3];
		int compBall = info[comp_idx][4];
		int strike = 0;
		int ball = 0;

		if (comp1 == num1)
			strike++;
		if (comp2 == num2)
			strike++;
		if (comp3 == num3)
			strike++;
		if (strike != compStrike) {
			check[Number] = false;
			return;
		}

		if ( comp2 == num1 || comp3 == num1) {
			ball++;
		}
		if (comp1 == num2 || comp3 == num2) {
			ball++;
		}
		if (comp1 == num3 || comp2 == num3) {
			ball++;
		}

		if (ball != compBall) {
			check[Number] = false;
			return;
		}

		check[Number] = true;
	}

	private static void FirstWork() {
		for (int j = 123; j < 988; j++) {
			String num = String.valueOf(j);
			int num1 = num.charAt(0) - '0';
			int num2 = num.charAt(1) - '0';
			int num3 = num.charAt(2) - '0';

			if (num1 == num2 || num2 == num3 || num1 == num3) {
				check[j] = false;
			} else if (num2 == 0 || num3 == 0) {
				check[j] = false;
			} else {
				check[j] = true;
			}
		}

	}

}
