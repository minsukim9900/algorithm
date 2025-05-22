import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static ArrayList<String> arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			arr = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			permutation(0, new char[N - 1], 1, 1);
			System.out.println();
		}
	}

	/**
	 * '+' , '-', ' ' 세 개중에 하나를 넣으면 됨. 부호는 N - 1개를 넣고 그 이후 0이 되는지 확인하는 로직을 구성해보자.
	 * 숫자는 오름차순이다. 완탐으로 어떻게 접근할 수 있을까? 각 숫자 사이에 3개의 부호를 넣고 계산하는 방식? 순열이다.
	 */

	private static void permutation(int depth, char[] select, int sum, int pre) {
		if (depth == N - 1) {
			if (sum == 0) {
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < N - 1; i++) {
					sb.append(i + 1).append(select[i]);
				}
				sb.append(N);
				System.out.println(sb.toString());
			}
		} else {

			select[depth] = ' ';
			if (pre < 0) {
				permutation(depth + 1, select, sum - (pre) + (pre * 10 - (depth + 2)), (pre * 10 - (depth + 2)));
			} else {
				permutation(depth + 1, select, sum - (pre) + (pre * 10 + (depth + 2)), (pre * 10 + (depth + 2)));
			}

			select[depth] = '+';
			permutation(depth + 1, select, sum + (depth + 2), depth + 2);

			select[depth] = '-';
			permutation(depth + 1, select, sum - (depth + 2), -(depth + 2));

		}
	}

}