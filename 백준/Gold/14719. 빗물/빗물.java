import java.io.*;
import java.util.*;

public class Main {

	private static int H, W;
	private static int[] heights;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		heights = new int[W];

		st = new StringTokenizer(br.readLine());
		int max = 0;
		int maxIdx = 0;

		for (int i = 0; i < W; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, heights[i]);

			if (max == heights[i]) {
				maxIdx = i;
			}

		}
		
		System.out.println(leftSearch(maxIdx) + rightSearch(maxIdx));

	}

	private static int leftSearch(int idx) {

		int max = -1;
		int sum = 0;
		for (int i = 0; i < idx; i++) {
			if (max < heights[i]) {
				max = heights[i];
			}
			sum += (max - heights[i]);
		}

		return sum;
	}

	private static int rightSearch(int idx) {
		
		int max = -1;
		int sum = 0;
		for (int i = W - 1; i > idx; i--) {
			if( max < heights[i]) {
				max = heights[i];
			}
			
			sum += (max - heights[i]);
		}

		return sum;
	}

}
