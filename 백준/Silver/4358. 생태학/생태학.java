import java.io.*;
import java.util.*;

public class Main {
	private static final int MAX = 10_001;
	private static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int index = 0;
		count = new int[MAX];
		Map<String, Integer> map = new HashMap<>();

		String word;
		int total = 0;
		TreeSet<String> ts = new TreeSet<>((a, b) -> a.compareTo(b));
		while ((word = br.readLine()) != null) {
			if (!map.containsKey(word)) {
				map.put(word, index++);
				ts.add(word);
			}
			total++;
			count[map.get(word)]++;
		}

		for (String str : ts) {
			int cnt = count[map.get(str)];
			double result = (double) cnt * 100 / total;
			sb.append(str).append(" ").append(String.format("%.4f", result)).append("\n");
		}
		System.out.println(sb.toString());
	}
}