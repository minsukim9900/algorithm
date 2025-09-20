import java.io.*;
import java.util.*;;

public class Main {
	private static int N, C;
	private static int[] count;

	private static class Number {
		int index;
		int value;
		int count;

		public Number(int index, int value) {
			super();
			this.index = index;
			this.value = value;
			this.count = 0;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		count = new int[N + 1];
		int idx = 0;

		Map<Integer, Number> db = new HashMap<>();
		List<Number> rc = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (!db.containsKey(num)) {
				Number number = new Number(idx++, num);
				db.put(num, number);
				rc.add(number);
			}
			db.get(num).count++;
		}
		Collections.sort(rc, (a, b) -> a.count == b.count ? a.index - b.index : b.count - a.count);
		
		for(Number num : rc) {
			while(num.count-- > 0) {
				sb.append(num.value).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
}