import java.io.*;
import java.util.*;

public class Main {
	private static int[] count = new int['Z' - 'A' + 1];

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			count[str.charAt(i) - 'A']++;
		}

		int tmp = 0;
		int idx = 0;
		for (int i = 0; i < count.length; i++) {
			if (count[i] % 2 == 1) {
				tmp++;
				idx = i;
			}

			if (tmp > 1) {
				System.out.println("I'm Sorry Hansoo");
				return;
			}
		}

		char[] result = new char[str.length()];
		if (tmp == 1) {
			int mid = str.length() / 2;
			result[mid] = (char) ('A' + idx);
			count[idx]--;

			int index = 0;
			for (int i = 0; i < count.length; i++) {
				for (int j = 0; j < count[i] / 2; j++) {
					result[index++] = (char) ('A' + i);
				}
				count[i] -= count[i] / 2;
			}
			index++;
			for (int i = count.length - 1; i >= 0; i--) {
				for (int j = 0; j < count[i]; j++) {
					result[index++] = (char) ('A' + i);
				}
			}
		} else {
			int index = 0;
			for (int i = 0; i < count.length; i++) {
				for (int j = 0; j < count[i] / 2; j++) {
					result[index++] = (char) ('A' + i);
				}
				count[i] -= count[i] / 2;
			}
			for (int i = count.length - 1; i >= 0; i--) {
				for (int j = 0; j < count[i]; j++) {
					result[index++] = (char) ('A' + i);
				}
			}
		}
		System.out.println(String.valueOf(result));
	}
}