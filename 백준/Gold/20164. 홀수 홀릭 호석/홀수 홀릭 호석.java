import java.io.*;
import java.util.*;

public class Main {
	private static int max, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		max = 0;
		min = Integer.MAX_VALUE;

		String number = br.readLine();
		comb(1, 0, new int[2], number, countOddNumber(number));
		sb.append(min).append(" ").append(max);
		System.out.println(sb.toString());
	}

	private static void comb(int idx, int depth, int[] result, String number, int count) {
		int n = number.length();
		if (depth == 2) {
			int sum = 0;
			for (int i = 0; i < 2; i++) {
				int temp = 0;

				if (i == 0) {
					for (int j = 0; j < result[0]; j++) {
						temp = temp * 10 + (number.charAt(j) - '0');
					}
				} else if (i == 1) {
					for (int j = result[0]; j < result[1]; j++) {
						temp = temp * 10 + (number.charAt(j) - '0');
					}
				}
				sum += temp;
			}

			int temp = 0;
			for (int i = result[1]; i < n; i++) {
				temp = temp * 10 + (number.charAt(i) - '0');
			}

			sum += temp;
			String nextValue = String.valueOf(sum);

			int nextCount = countOddNumber(nextValue);

			if (nextValue.length() > 2) {
				comb(1, 0, new int[2], nextValue, count + nextCount);
			} else {
				nextJob(nextValue, count);
			}
			return;
		}

		for (int i = idx; i < n; i++) {
			result[depth] = i;
			comb(i + 1, depth + 1, result, number, count);
		}
	}

	private static void nextJob(String number, int count) {
		int n = number.length();

		if (n <= 1) {
			int x = Integer.parseInt(number);

			int result = count;
			if (x % 2 == 1) {
				result++;
			}
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		int x = number.charAt(0) - '0';
		int y = number.charAt(1) - '0';

		int sum = 0;

		if (x % 2 == 1) {
			sum++;
		}
		if (y % 2 == 1) {
			sum++;
		}
		String nextNumber = String.valueOf((x + y));
		nextJob(nextNumber, count + sum);
	}

	private static int countOddNumber(String number) {
		char[] nums = number.toCharArray();

		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int temp = nums[i] - '0';
			if (temp % 2 == 1) {
				count++;
			}
		}

		return count;
	}
}