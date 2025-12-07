import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		List<Integer> negative = new ArrayList<>();
		List<Integer> positive = new ArrayList<>();
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num <= 0) {
				negative.add(num);
			} else if (num == 1) {
				cnt++;
			} else {
				positive.add(num);
			}
		}

		negative.sort(Collections.reverseOrder());
		Collections.sort(positive);
		System.out.println(cal(negative) +cal(positive) + cnt);
	}

	private static int cal(List<Integer> number) {
		int result = 0;
		for (int i = number.size() - 1; i > 0; i -= 2) {
			result += number.get(i) * number.get(i - 1);
		}
		
		if(number.size() % 2 == 1) {
			result += number.get(0);
		}
		return result;
	}
}