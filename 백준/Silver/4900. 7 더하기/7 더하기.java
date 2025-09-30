import java.io.*;
import java.util.*;

public class Main {
	private static Map<Integer, Integer> changeNum = new HashMap<>();
	private static Map<Character, String> changeStr = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			String temp = br.readLine();
			if (temp.equals("BYE"))
				break;

			char[][] expression = preProcessing(temp);

			int answer = 0;
			for (int i = 0; i < 2; i++) {
				int midSum = 0;
				for (int j = 0; j < expression[i].length; j += 3) {
					int microSum = 0;
					for (int k = 0; k < 3; k++) {
						microSum = microSum * 10 + (expression[i][j + k] - '0');
					}

					int rs = changeNum.get(microSum);
					midSum = midSum * 10 + (rs);
				}
				answer += midSum;
			}
			sb.append(temp + changeString(answer)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static String changeString(int num) {
		String str = String.valueOf(num);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			String tmp = changeStr.get(c);
			if(tmp.length() == 2) {
				sb.append("0");
			}
			sb.append(tmp);
		}
		return sb.toString();
	}

	private static char[][] preProcessing(String expression) {
		int zero = 0b0111111;
		int one = 0b0001010;
		int two = 0b1011101;
		int three = 0b1001111;
		int four = 0b1101010;
		int five = 0b1100111;
		int six = 0b1110111;
		int seven = 0b0001011;
		int eight = 0b1111111;
		int nine = 0b1101011;

		changeNum.put(zero, 0);
		changeNum.put(one, 1);
		changeNum.put(two, 2);
		changeNum.put(three, 3);
		changeNum.put(four, 4);
		changeNum.put(five, 5);
		changeNum.put(six, 6);
		changeNum.put(seven, 7);
		changeNum.put(eight, 8);
		changeNum.put(nine, 9);
		
		changeStr.put('0', String.valueOf(zero));
		changeStr.put('1', String.valueOf(one));
		changeStr.put('2', String.valueOf(two));
		changeStr.put('3', String.valueOf(three));
		changeStr.put('4', String.valueOf(four));
		changeStr.put('5', String.valueOf(five));
		changeStr.put('6', String.valueOf(six));
		changeStr.put('7', String.valueOf(seven));
		changeStr.put('8', String.valueOf(eight));
		changeStr.put('9', String.valueOf(nine));

		String[] str = expression.split("\\+");
		char[][] states = new char[2][];
		states[0] = str[0].toCharArray();
		states[1] = new char[str[1].length() - 1];

		for (int i = 0; i < str[1].length() - 1; i++) {
			states[1][i] = str[1].charAt(i);
		}
		return states;
	}
}
