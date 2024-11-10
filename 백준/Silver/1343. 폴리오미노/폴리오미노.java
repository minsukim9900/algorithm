import java.io.*;
import java.util.*;

public class Main {
	private static int cnt;
	private static StringBuilder sb = new StringBuilder();
	private static boolean check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String poliomino = br.readLine();
		int N = poliomino.length();

		check = true;

		for (int i = 0; i < N; i++) {
			char c = poliomino.charAt(i);
			if (!check) {
				break;
			} else if (c == '.' && check) {
				convert();
				sb.append(".");
				cnt = 0;
			} else {
				cnt++;
			}
		}
		
		convert();

		if (check) {
			System.out.println(sb.toString());
		}else {
			System.out.println(-1);
		}
		

	}

	public static void convert() {
		if (cnt % 2 == 1) {
			check = false;
		} else if (cnt == 2) {
			sb.append("BB");
		} else if (cnt % 4 == 0) {
			int tmp = cnt / 4;
			for (int i = 0; i < tmp; i++) {
				sb.append("AAAA");
			}
		} else if (cnt % 4 == 2) {
			int tmp = cnt / 4;
			for (int i = 0; i < tmp; i++) {
				sb.append("AAAA");
			}
			sb.append("BB");
		}
	}

}
