import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		ArrayList<String> info = new ArrayList<>();

		int T = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String comp = str;
		int t = 0;
		info.add(str);

		while (true) {

			sb = new StringBuilder();

			for (int i = 0; i < str.length(); i+=2) {
					sb.append(str.charAt(i));
			}

			for(int i = str.length() - 1; i>=0; i--) {
				if(i % 2 == 1) {
					sb.append(str.charAt(i));
				}
			}
			
			str = sb.toString();
			t++;
			if (str.equals(comp))
				break;
			info.add(str);
		}

		System.out.println(info.get(T % t));

	}
}
