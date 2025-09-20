import java.io.*;
import java.util.*;;

public class Main {
	private static int N, C;
	private static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		count = new int[N + 1];
		int idx = 0;

		Map<Integer, Integer> numDatabase = new HashMap<>();
		Map<Integer, Integer> indexDatabase = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (numDatabase.containsKey(num)) {
				count[numDatabase.get(num)]++;
			} else {
				numDatabase.put(num, ++idx);
				count[idx]++;
				indexDatabase.put(idx, num);
			}
		}

		int[][] info = new int[idx][3];
		for (int i = 0; i < idx; i++) {
			info[i][0] = indexDatabase.get(i + 1);
			info[i][1] = numDatabase.get(info[i][0]);
			info[i][2] = count[i + 1];
		}
		Arrays.sort(info, (a, b) -> a[2] == b[2] ? a[1] - b[1] : b[2] - a[2]);
		for (int i = 0; i < info.length; i++) {
			for (int j = 0; j < info[i][2]; j++) {
				sb.append(info[i][0]).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
}