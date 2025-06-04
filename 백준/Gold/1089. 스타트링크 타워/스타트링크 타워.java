import java.io.*;
import java.util.*;

class Main {
	private static int N, count;
	private static double sum = 0.0;
	private static Map<Integer, Integer> map = new HashMap<>();
	private static Map<Integer, Integer> map2 = new HashMap<>();
	private static Set<Double> set = new HashSet<>();

	// [31599, 18724, 29671, 31207, 18925, 31183, 31695, 18727, 31727, 31215]
	static {
		map.put(0, 10);
		map.put(31599, 0);
		map.put(18724, 1);
		map.put(29671, 2);
		map.put(31207, 3);
		map.put(18925, 4);
		map.put(31183, 5);
		map.put(31695, 6);
		map.put(18727, 7);
		map.put(31727, 8);
		map.put(31215, 9);

		map2.put(0, 31599);
		map2.put(1, 18724);
		map2.put(2, 29671);
		map2.put(3, 31207);
		map2.put(4, 18925);
		map2.put(5, 31183);
		map2.put(6, 31695);
		map2.put(7, 18727);
		map2.put(8, 31727);
		map2.put(9, 31215);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		String[] str = new String[5];

		for (int i = 0; i < 5; i++) {
			str[i] = br.readLine();
		}

		int[] number = new int[N];
		int idx = 0;
		for (int i = 0; i < (N * 3) + (N - 1); i += 4) {
			int x = 0;

			for (int j = 0; j < 5; j++) {
				for (int k = i; k < i + 3; k++) {
					String curr = str[j];
					char c = curr.charAt(k);

					if (c == '#') {
						number[idx] |= (1 << x);
					}
					x++;
				}
			}
			idx++;
		}

		ArrayList<Integer>[] tmp = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tmp[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			int curr = number[i];

			for (int j = 0; j < 10; j++) {
				if ((curr & map2.get(j)) == curr) {
					tmp[i].add(j);
				}
			}

			if (tmp[i].isEmpty()) {
				System.out.println(-1);
				return;
			}
		}
		
		double answer = 0.0;
		double weight = 1.0;  // 맨 오른쪽 자리(10^0=1)부터 시작

		for (int i = N - 1; i >= 0; i--) {
		    long sumDigits = 0;
		    int cnt = tmp[i].size();
		    for (int d : tmp[i]) {
		        sumDigits += d;
		    }
		    double avgDigit = (double) sumDigits / cnt;
		    answer += avgDigit * weight;
		    weight *= 10.0;
		}

		System.out.printf("%.5f\n", answer);
    }
}