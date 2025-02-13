import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			sb = new StringBuilder();
			LinkedList<Character> list = new LinkedList<>();
			String str = br.readLine();
			int idx = 0;
			for (int j = 0; j < str.length(); j++) {
				char tmp = str.charAt(j);
				switch (tmp) {
				case '<':
					if (idx == 0)
						continue;
					idx--;
					break;
				case '>':
					if (idx == list.size())
						continue;
					idx++;
					break;
				case '-':
					if (idx == 0)
						continue;
					list.remove(--idx);
					break;
				default:
					list.add(idx++, tmp);
					break;
				}
			}
			
			for(Character c : list) {
				sb.append(c);
			}
			System.out.println(sb);

		}
		
		

	}

}
