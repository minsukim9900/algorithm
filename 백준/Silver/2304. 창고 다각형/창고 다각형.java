import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		ArrayList<int[]> info = new ArrayList<>();

		int max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			max = Math.max(max, y);

			info.add(new int[] { x, y });
		}

		Collections.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int mid = findIdx(info, max);
		System.out.println(cal(info, mid));

	}

	private static int findIdx(ArrayList<int[]> info, int max) {
		int idx = 0;
		for (int i = 0; i < info.size(); i++) {
			if (max == info.get(i)[1])
				idx = i;
		}

		return idx;
	}

	private static int cal(ArrayList<int[]> info, int idx) {

		int sum = 0;
		int max = info.get(0)[1];
		int preidx = info.get(0)[0];
		
		for (int i = 1; i <= idx; i++) {
			int[] curr = info.get(i);
			int tmp = curr[0] - preidx;
			if (curr[1] > max) {
				sum += (max * tmp);
				max = curr[1];
			} else {
				sum += (max * tmp);
			}
			
			preidx = curr[0];

		}
		
		
		max = info.get(info.size()-1)[1];
		preidx = info.get(info.size() - 1)[0];
		
		for (int i = info.size() - 2; i >= idx; i--) {
			int[] curr = info.get(i);
			int tmp = preidx - curr[0];
			
			if (curr[1] > max) {
				sum += (max * tmp);
				max = curr[1];
			} else {
				sum += (max * tmp);
			}
			preidx = curr[0];
		}

		return sum + info.get(idx)[1];
	}
}
