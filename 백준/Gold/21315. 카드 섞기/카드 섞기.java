import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[] nums;
	private static int[] cards, original;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		cards = new int[N];
		original = new int[N];
		for (int i = 0; i < N; i++) {
			cards[i] = i + 1;
			original[i] = i + 1;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		for (int firstK = 1; firstK <= N; firstK++) {
			cards = original.clone();
			int maxA = (int) Math.pow(2, firstK);

			if (maxA > N)
				break;

			shuffle(firstK, false);
			for (int secondK = 1; secondK <= N; secondK++) {
				int maxB = (int) Math.pow(2, secondK);

				if (maxB > N)
					break;
				if (shuffle(secondK, true)) {
					sb.append(firstK).append(" ").append(secondK);
					System.out.println(sb.toString());
					return;
				}
			}
		}
	}

	private static boolean shuffle(int K, boolean state) {
		int[] card = new int[N];
		int range = (int) Math.pow(2, K);

		int idx = 0;
		for (int i = N - range; i < N; i++) {
			card[idx++] = cards[i];
		}

		for (int i = 0; i < N - range; i++) {
			card[idx++] = cards[i];
		}

		for (int i = 2; i <= K + 1; i++) {
			int[] temp = new int[N];
			int nextRange = (int) Math.pow(2, K - i + 1);
			idx = 0;
			for (int j = range - nextRange; j < range; j++) {
				temp[idx++] = card[j];
			}

			for (int j = 0; j < range - nextRange; j++) {
				temp[idx++] = card[j];
			}

			for (int j = range; j < N; j++) {
				temp[idx++] = card[j];
			}

			card = temp;
			range = nextRange;
		}

		if (state && check(card)) {
			return true;
		}
		if (!state) {
			cards = card.clone();
		}
		return false;
	}

	private static boolean check(int[] comp) {
		for (int i = 0; i < N; i++) {
			if (nums[i] != comp[i])
				return false;
		}
		return true;
	}
}