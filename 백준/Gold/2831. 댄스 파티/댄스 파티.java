import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static List<Integer> womenWantTaller;
	private static List<Integer> womenWantShorter;
	private static List<Integer> menWantTaller;
	private static List<Integer> menWantShorter;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		menWantTaller = new ArrayList<>();
		menWantShorter = new ArrayList<>();
		womenWantTaller = new ArrayList<>();
		womenWantShorter = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (num > 0) {
				menWantTaller.add(num);
			} else {
				menWantShorter.add(num);
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (num > 0) {
				womenWantTaller.add(num);
			} else {
				womenWantShorter.add(num);
			}
		}

		Collections.sort(menWantTaller);
		Collections.sort(menWantShorter, Comparator.reverseOrder());
		Collections.sort(womenWantTaller);
		Collections.sort(womenWantShorter, Comparator.reverseOrder());

		System.out.println(matchingCouple(menWantTaller, womenWantShorter, true)
				+ matchingCouple(menWantShorter, womenWantTaller, false));
	}

	private static int matchingCouple(List<Integer> men, List<Integer> women, boolean state) {
		if (men.isEmpty() || women.isEmpty())
			return 0;

		int count = 0;

		int menIndex = 0;
		int womenIndex = 0;

		while (menIndex < men.size() && womenIndex < women.size()) {
			int manHeight = Math.abs(men.get(menIndex));
			int womanHeight = Math.abs(women.get(womenIndex));

			boolean result = state ? manHeight < womanHeight : manHeight > womanHeight;

			if (result) {
				count++;
				menIndex++;
				womenIndex++;
			} else {
				if (state) {
					womenIndex++;
				} else {
					menIndex++;
				}
			}
		}
		return count;
	}
}