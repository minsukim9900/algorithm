import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static Map<Integer, String> book = new HashMap<>();
	private static Map<String, Integer> book2 = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			String tmp = br.readLine();
			book.put(i, tmp);
			book2.put(tmp, i);
		}

		for (int i = 0; i < M; i++) {
			String tmp = br.readLine();
			char c = tmp.charAt(0);
			if(Character.isDigit(c)) {
				sb.append(book.get(Integer.parseInt(tmp))).append("\n");
			}else {
				sb.append(book2.get(tmp)).append("\n");
			}
		}
		
		System.out.println(sb.toString());

	}
}
