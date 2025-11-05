import java.io.*;
import java.util.*;

public class Main {
	private static int[] count = new int[1001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int x = Integer.parseInt(br.readLine());

		for (int i = 1; i < 1000; i++) {
			if (i >= 1 && i < 100) {
				count[i] = i;
			} else {
				if (isPoss(String.valueOf(i).toCharArray())) {
					count[i] = count[i - 1] + 1;
				} else {
					count[i] = count[i - 1];
				}
			}
		}

		count[1000] = count[999];
		System.out.println(count[x]);
	}

	private static boolean isPoss(char[] num) {
		return (num[0] - num[1]) == (num[1] - num[2]);
	}
}