import java.io.*;
import java.util.*;

public class Solution {

	private static ArrayDeque<Integer>[] wheel;
	private static int[] dir;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=testCase; t++) {
			
			wheel = new ArrayDeque[5];
			for (int i = 1; i <= 4; i++) {
				wheel[i] = new ArrayDeque<>();
			}
			
			int T = Integer.parseInt(br.readLine());
			
			for (int i = 1; i <= 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 8; j++) {
					wheel[i].offer(Integer.parseInt(st.nextToken()));
				}
				
			}
			
			
			for (int k = 0; k < T; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				int rotate = Integer.parseInt(st.nextToken());
				process(idx, rotate);
				
			}
			
			int result = 0;
			int tmp = 1;
			for (int i = 1; i <= 4; i++) {
				result += (wheel[i].peek() * tmp);
				tmp *= 2;
			}
			
			System.out.println("#" + t + " " + result);
		}

	}

	private static void process(int idx, int rotate) {

		dir = new int[5];
		dir[idx] = rotate;

		int tmp = rotate;
		for (int i = idx; i > 1; i--) {

			int[] left = new int[2];
			int[] right = new int[2];

			left[0] = wheel[i].removeLast();
			left[1] = wheel[i].removeLast();
			right[0] = wheel[i - 1].poll();
			right[1] = wheel[i - 1].poll();

			if (left[1] != wheel[i - 1].peek()) {
				tmp *= -1;
				dir[i - 1] = tmp;
				wheel[i].offer(left[1]);
				wheel[i].offer(left[0]);
				wheel[i - 1].addFirst(right[1]);
				wheel[i - 1].addFirst(right[0]);
			} else {
				wheel[i].offer(left[1]);
				wheel[i].offer(left[0]);
				wheel[i - 1].addFirst(right[1]);
				wheel[i - 1].addFirst(right[0]);
				break;
			}

		}
		tmp = rotate;
		for (int i = idx; i < 4; i++) {

			int[] left = new int[2];
			int[] right = new int[2];

			left[0] = wheel[i].poll();
			left[1] = wheel[i].poll();
			right[0] = wheel[i + 1].removeLast();
			right[1] = wheel[i + 1].removeLast();

			if (wheel[i].peek() != right[1]) {
				tmp *= -1;
				dir[i + 1] = tmp;
				wheel[i].addFirst(left[1]);
				wheel[i].addFirst(left[0]);
				wheel[i + 1].offer(right[1]);
				wheel[i + 1].offer(right[0]);
			} else {
				wheel[i].addFirst(left[1]);
				wheel[i].addFirst(left[0]);
				wheel[i + 1].offer(right[1]);
				wheel[i + 1].offer(right[0]);
				break;
			}

		}

		turn();
	}

	private static void turn() {
		for (int i = 1; i <= 4; i++) {

			if (dir[i] == 1) {
				int tmp = wheel[i].removeLast();
				wheel[i].addFirst(tmp);
			} else if (dir[i] == -1) {
				int tmp = wheel[i].poll();
				wheel[i].offer(tmp);
			}

		}
	}


}
