import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str = br.readLine();

		int index = 0;
		int answer = 0;
		out: for (int i = 1; i <= 30000; i++) {
			String comp = String.valueOf(i);

			for (int j = 0; j < comp.length(); j++) {
				if (str.charAt(index) == comp.charAt(j)) {
					answer = i;
					index++;
				}

				if (index == str.length())
					break out;
			}

		}
		System.out.println(answer);
	}
}