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
			sb = new StringBuilder();
			arr = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			permutation(0, new char[N - 1]);
			
			Collections.sort(arr);
			
			for(String str : arr) {
				sb.append(str);
			}
			System.out.println(sb.toString());
		}
	}

	/**
	 * '+' , '-', ' ' 세 개중에 하나를 넣으면 됨. 부호는 N - 1개를 넣고 그 이후 0이 되는지 확인하는 로직을 구성해보자.
	 * 숫자는 오름차순이다. 완탐으로 어떻게 접근할 수 있을까? 각 숫자 사이에 3개의 부호를 넣고 계산하는 방식? 순열이다.
	 */

	private static void permutation(int num, char[] select) {
		if (num == N - 1) {
			StringBuilder sb = new StringBuilder();
			if (cal(select) == 0) {
				for (int i = 0; i < N - 1; i++) {
					sb.append(i + 1);
					sb.append(select[i]);
				}
				sb.append(N).append("\n");
				arr.add(sb.toString());
			}
		} else {
			select[num] = '+';
			permutation(num + 1, select);

			select[num] = '-';
			permutation(num + 1, select);

			select[num] = ' ';
			permutation(num + 1, select);

		}
	}

	private static int cal(char[] select) {
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < N - 1; i++) {
			sb1.append(i + 1);
			sb1.append(select[i]);
		}
		sb1.append(N);

		String expression = sb1.toString().replace(" ", "");

		int sum = 0;
		int num = expression.charAt(0) - '0';
		char op = '+';

		for (int i = 1; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (c >= '0' && c <= '9') {
				num = num * 10 + (c - '0');
			} else {
				if(op == '+') {
					sum += num;
				}else {
					sum -= num;
				}
				
				op = c;
				num = 0;
			}
		}
		
		if(op == '+') {
			sum += num;
		}else {
			sum -= num;
		}
		
		return sum;
	}
}