import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str = br.readLine();
		Stack<Character> stack = new Stack<>();

		int sum = 0;
		int cnt = 0;
		char pre = '0';
		boolean isSame;

		for (int i = 0; i < str.length(); i++) {
			isSame = false;
			char tmp = str.charAt(i);

			if (pre == tmp)
				isSame = true;
			pre = tmp;

			if (tmp == '(') {

				cnt++;

			} else {
					cnt--;
					if (isSame) {
						
						sum += 1;
					} else {
						sum += cnt;
					}
				}

			}

		
		System.out.println(sum);

	}

}