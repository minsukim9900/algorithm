import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;
	private static int[] s, p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		p = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}

		s = new int[N];
		int[] cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			s[i] = Integer.parseInt(st.nextToken());
			cards[i] = i;
		}

		simulate(cards, 0);
		System.out.println(answer);
	}

	private static void simulate(int[] cards, int count) {
		if (checkPossible(cards)) {
			answer = count;
			return;
		}

		if (count > 0 && checkInfinite(cards)) {
			answer = -1;
			return;
		}

		int[] result = shuffle(cards);
		simulate(result, count + 1);
	}

	private static int[] shuffle(int[] cards) {
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			result[s[i]] = cards[i];
		}
		return result;
	}

	private static boolean checkPossible(int[] cards) {
		for (int i = 0; i < N; i++) {
			int cardNum = cards[i];
			int player = i % 3;
			if (p[cardNum] != player)
				return false;
		}
		return true;
	}

	private static boolean checkInfinite(int[] cards) {
		for (int i = 0; i < N; i++) {
			if (i != cards[i])
				return false;
		}
		return true;
	}
}