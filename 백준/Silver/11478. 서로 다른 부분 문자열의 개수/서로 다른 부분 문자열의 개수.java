import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		TreeSet<String> ts = new TreeSet<>();

		for (int s = 0; s < str.length(); s++) {
			for (int e = s + 1; e <= str.length(); e++) {
				String tmp = str.substring(s, e);
				if (tmp != "") {
					ts.add(tmp);
				}
			}
		}
		System.out.println(ts.size());
	}
}