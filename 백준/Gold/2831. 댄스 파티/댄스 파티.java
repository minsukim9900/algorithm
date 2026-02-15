import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static List<Integer> prefersTallerWomen;
	private static List<Integer> prefersShorterWomen;
	private static List<Integer> prefersTallerMen;
	private static List<Integer> prefersShorterMen;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		prefersTallerWomen = new ArrayList<>();
		prefersShorterWomen = new ArrayList<>();
		prefersTallerMen = new ArrayList<>();
		prefersShorterMen = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (num > 0) {
				prefersTallerMen.add(num);
			} else {
				prefersShorterMen.add(num * -1);
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (num > 0) {
				prefersTallerWomen.add(num);
			} else {
				prefersShorterWomen.add(num * -1);
			}
		}

		prefersTallerMen.sort((a, b) -> Integer.compare(a, b));
		prefersShorterMen.sort((a, b) -> Integer.compare(a, b));
		prefersTallerWomen.sort((a, b) -> Integer.compare(a, b));
		prefersShorterWomen.sort((a, b) -> Integer.compare(a, b));

		System.out.println(matchingTallerWomenAndShorterMenCouple(prefersTallerMen, prefersShorterWomen)
				+ matchingShorterWomenAndTallerMenCouple(prefersShorterMen, prefersTallerWomen));
	}

	private static int matchingTallerWomenAndShorterMenCouple(List<Integer> men, List<Integer> women) {
		if (men.isEmpty() || women.isEmpty())
			return 0;

		int count = 0;

		int menIndex = 0;
		int womenIndex = 0;

		while (menIndex < men.size() && womenIndex < women.size()) {
			int manHeight = men.get(menIndex);
			int womanHeight = women.get(womenIndex);

			if (manHeight >= womanHeight) {
				womenIndex++;
			} else {
				count++;
				menIndex++;
				womenIndex++;
			}
		}
		return count;
	}

	private static int matchingShorterWomenAndTallerMenCouple(List<Integer> men, List<Integer> women) {
		if (men.isEmpty() || women.isEmpty())
			return 0;

		int count = 0;

		int menIndex = 0;
		int womenIndex = 0;

		while (menIndex < men.size() && womenIndex < women.size()) {
			int manHeight = men.get(menIndex);
			int womanHeight = women.get(womenIndex);

			if (manHeight <= womanHeight) {
				menIndex++;
			} else {
				count++;
				menIndex++;
				womenIndex++;
			}
		}
		return count;
	}
}