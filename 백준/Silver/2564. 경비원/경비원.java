import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 가로 길이
		int c = Integer.parseInt(st.nextToken()); // 세로 길이

		int n = Integer.parseInt(br.readLine());

		int[] location = new int[n];
		int[][] distances = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			location[i] = Integer.parseInt(st.nextToken());
			int temp = Integer.parseInt(st.nextToken());

			if (location[i] == 1 || location[i] == 2) {
				distances[i][0] = temp;
				distances[i][1] = r - temp;
			} else if (location[i] == 3 || location[i] == 4) {
				distances[i][0] = temp;
				distances[i][1] = c - temp;
			}
		}

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()); // 방 향
		int y = Integer.parseInt(st.nextToken()); // 칸 수

		int[] temp = new int[2]; // 맨 앞에서 뺄 때
		if (x == 1 || x == 2) { // 북쪽 방향, 남쪽 방향
			temp[0] = y;
			temp[1] = r - y;
		} else if (x == 3 || x == 4) {
			temp[0] = y;
			temp[1] = c - y;
		}

		int sum = 0;
		
		
		for (int i = 0; i < n; i++) {
			int min = 999999999;

			if (location[i] == 1 && x == 2) {
				if(min > (distances[i][0]+temp[0]+c)) {
					min = distances[i][0]+temp[0]+c;
				}
				if(min> (distances[i][1]+temp[1]+c)) {
					min = distances[i][1]+temp[1]+c;
				}
				sum += min;
			}else if (location[i] == 2 && x == 1) {
				if(min > (distances[i][0]+temp[0]+c)) {
					min = distances[i][0]+temp[0]+c;
				}
				if(min> (distances[i][1]+temp[1]+c)) {
					min = distances[i][1]+temp[1]+c;
				}
				sum += min;
			}
			else if (location[i] == 3 && x == 4) {
				if(min > (distances[i][0]+temp[0]+r)) {
					min = distances[i][0]+temp[0]+r;
				}
				if(min> (distances[i][1]+temp[1]+r)) {
					min = distances[i][1]+temp[1]+r;
				}
				sum += min;
			}
			else if (location[i] == 4 && x == 3) {
				if(min > (distances[i][0]+temp[0]+r)) {
					min = distances[i][0]+temp[0]+r;
				}
				if(min> (distances[i][1]+temp[1]+r)) {
					min = distances[i][1]+temp[1]+r;
				}
				sum += min;
			}
			else if (location[i] == 1 && x == 4) {
				if (min > (distances[i][1] + temp[0])) {
					min = distances[i][1] + temp[0];
				}
				if (min > distances[i][0] + c + r + temp[1]) {
					min = distances[i][0] + c + r + temp[1];
				}
				sum += min;

			} else if (location[i] == 2 && x == 4) {
				if (min > (distances[i][1] + temp[1])) {
					min = distances[i][1] + temp[1];
				}
				if (min > distances[i][0] + c + r + temp[0]) {
					min = distances[i][0] + c + r + temp[0];
				}
				sum += min;

			}
			else if (location[i] == 1 && x == 3) {
				if (min > (distances[i][0] + temp[0])) {
					min = distances[i][0] + temp[0];
				}
				if (min > distances[i][1] + c + r + temp[1]) {
					min = distances[i][1] + c + r + temp[1];
				}
				sum += min;

			}
			else if (location[i] == 2 && x == 3) {
				if (min > (distances[i][1] + temp[0])) {
					min = distances[i][1] + temp[0];
				}
				if (min > distances[i][0] + c + r + temp[0]) {
					min = distances[i][0] + c + r + temp[0];
				}
				sum += min;

			}
			else if (location[i] == 3 && x == 1) {
				if (min > (distances[i][0] + temp[0])) {
					min = distances[i][0] + temp[0];
				}
				if (min > distances[i][1] + c + r + temp[1]) {
					min = distances[i][1] + c + r + temp[1];
				}
				sum += min;

			}
			else if (location[i] == 3 && x == 2) {
				if (min > (distances[i][1] + temp[0])) {
					min = distances[i][1] + temp[0];
				}
				if (min > distances[i][0] + c + r + temp[1]) {
					min = distances[i][0] + c + r + temp[1];
				}
				sum += min;

			}
			else if (location[i] == 4 && x == 1) {
				if (min > (distances[i][0] + temp[1])) {
					min = distances[i][0] + temp[1];
				}
				if (min > distances[i][1] + c + r + temp[0]) {
					min = distances[i][1] + c + r + temp[0];
				}
				sum += min;

			}
			else if (location[i] == 4 && x == 2) {
				if (min > (distances[i][1] + temp[1])) {
					min = distances[i][1] + temp[1];
				}
				if (min > distances[i][0] + c + r + temp[0]) {
					min = distances[i][0] + c + r + temp[0];
				}
				sum += min;

			}
			else if (location[i] == x) {
				int num = Math.abs(distances[i][0] - temp[0]);
				sum += num;
			}
		}

		System.out.println(sum);

	}
}