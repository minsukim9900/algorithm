import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] square = new int[5][2];
	private static int[] check = new int[5];
	private static int[] num = new int[5];
	private static int[] index = new int[2];
	private static int max_width = Integer.MIN_VALUE;
	private static int max_hight = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < 6; i++) {
			int tmp = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			if(num[dir] == 0) {
				num[dir] = i+1;
			}
			if (dir == 4 || dir == 3) {
				if (max_hight < len) {
					max_hight = len;
				}
			} else if (dir == 1 || dir == 2) {
				if (max_width < len) {
					max_width = len;
				}
			}

			if (check[dir] == 1) {
				tmp++;
			}
			check[dir]++;
			square[dir][tmp] = len;
		}
		
		
		int idx = 0;
		for (int i = 1; i <= 4; i++) {
			if (check[i] == 2) {
				index[idx++] = i;
			}
		}
		
		
		
		
		int minuseArea = clearArea();
		System.out.println((max_width * max_hight - minuseArea)*N);

	}

	private static int clearArea() {

		if (index[0] == 1 && index[1] == 3) {
			if(num[3] == 1 && num[1] == 2 && num[4] == 3) {
				int tmp = square[1][1] * square[3][0];
				return tmp;
			}else if(num[1] == 1 && num[4] == 2 && num[2] == 3) {
				int tmp = square[1][1] * square[3][1];
				return tmp;
			}
			else if(num[index[0]] < num[index[1]]) { // 1 - 3 - 1 - 3 순서
				int tmp = square[1][0] * square[3][0];
				return tmp;
			}else if(num[index[0]] > num[index[1]]) { // 3 - 1 - 3 - 1 순서
				int tmp = square[1][0] * square[3][1];
				return tmp;
			}

		} else if (index[0] == 2 && index[1] == 3) {
			if(num[3] == 1  && num[1] == 2 && num[4] == 3) {
				int tmp = square[2][1] * square[3][1];
				return tmp;
			}else if(num[2] == 1 && num[3] == 2 && num[1] == 3) {
				int tmp = square[2][0] * square[3][1];
				return tmp;
			}
			else if(num[index[0]] < num[index[1]]) { // 2 - 3 - 2 - 3 순서
				int tmp = square[2][1] * square[3][0];
				return tmp;
			}else if(num[index[0]] > num[index[1]]) { // 3 - 2 - 3 - 2 순서
				int tmp = square[2][0] * square[3][0];
				return tmp;
			}

		} else if (index[0] == 1 && index[1] == 4) {
			if(num[4] == 1 && num[2] == 2 && num[3] == 3 ) {
				int tmp = square[1][1] * square[4][1];
				return tmp;
			}else if(num[1] == 1 && num[4] == 2 && num[2] == 3) {
				int tmp = square[1][0] * square[4][1];
				return tmp;
			}
			else if(num[index[0]] < num[index[1]]) { // 1 - 4 - 1 - 4
				int tmp = square[1][1] * square[4][0];
				return tmp;
			}else if(num[index[0]] > num[index[1]]){ 
				int tmp = square[1][0] * square[4][0];
				return tmp;
			}

		} else if (index[0] == 2 && index[1] == 4) {
			if(num[2] == 1 && num[3] == 2 && num[1] == 3) {
				int tmp = square[2][1] * square[4][1];
				return tmp;
			}else if(num[4] == 1 && num[2] == 2 && num[3] == 3) {
				int tmp = square[2][1] * square[4][0];
				return tmp;
			}
			else if(num[index[0]] < num[index[1]]) { // 2 - 4 - 2 - 4
				int tmp = square[2][0] * square[4][0];
				return tmp;
			}else if(num[index[0]] > num[index[1]]) { // 4 - 2 - 4 - 2
				int tmp = square[2][0] * square[4][1];
				return tmp;
			}
		}
		return 0;
	}

}
