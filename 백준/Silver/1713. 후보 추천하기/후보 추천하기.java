import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		List<int[]> frame = new ArrayList<>();
		Map<Integer, int[]> map = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int curr = Integer.parseInt(st.nextToken());

			if (map.containsKey(curr)) {
				map.get(curr)[1]++;
			} else {
				if (frame.size() == N) {
					int[] toRemove = frame.get(0);

					for (int[] comp : frame) {
						if (comp[1] < toRemove[1] || (comp[1] == toRemove[1] && comp[2] < toRemove[2])) {
							toRemove = comp;
						}
					}
					frame.remove(toRemove);
					map.remove(toRemove[0]);
				}

				int[] cand = new int[] { curr, 1, i };
				frame.add(cand);
				map.put(curr, cand);
			}
		}
		
		Collections.sort(frame, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		for(int[] w : frame) {
			sb.append(w[0]).append(" ");
		}
		System.out.println(sb.toString());
	}
}