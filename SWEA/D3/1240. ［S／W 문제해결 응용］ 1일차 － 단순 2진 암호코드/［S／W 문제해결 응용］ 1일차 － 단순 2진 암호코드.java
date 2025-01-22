import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			String nonPass = "";
			for (int i = 0; i < C; i++) {
				nonPass += "0";
			}

			boolean isFind = false;
			int idx = 0;
			String spyKey = null;

			for (int i = 0; i < R; i++) {

				String tmp = br.readLine();

				if (tmp.equals(nonPass))
					continue;

				for (int j = tmp.length() - 1; j >= 0; j--) {

					if (isFind)
						break;
					int num = tmp.charAt(j) - '0';

					if (!isFind && num == 1) {
						isFind = true;
						idx = j;
					}
				}

				if (isFind) {
					spyKey = tmp.substring(idx - 55, idx + 1);
				}

			}

			int oddSum = 0;
			int evenSum = 0;
			int time = 1;
			int sum = 0;
			for (int i = 0; i < 56; i += 7) {
				String tmp = spyKey.substring(i, i + 7);
				int num = decrypt(tmp);
				sum += num;
				if (time % 2 == 1) {
					oddSum += num;
				} else {
					evenSum += num;
				}
				time++;
			}
			
			if(((oddSum * 3) + evenSum) % 10 == 0) {
				System.out.println("#"+t+ " " +sum);
			}else {
				System.out.println("#"+t+ " " +0);
			}

		}
	}

	private static int decrypt(String num) {
		switch (num) {
		case "0001101":
			return 0;
		case "0011001":
			return 1;
		case "0010011":
			return 2;
		case "0111101":
			return 3;
		case "0100011":
			return 4;
		case "0110001":
			return 5;
		case "0101111":
			return 6;
		case "0111011":
			return 7;
		case "0110111":
			return 8;
		case "0001011":
			return 9;
		}
		return -1;
	}

}
