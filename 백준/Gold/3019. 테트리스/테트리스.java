import java.io.*;
import java.util.*;

public class Main {

	private static int C, P;
	private static int[] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		arr = new int[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(cal(P));

	}

	private static int cal(int num) {

		int cnt = 0;

		switch (num) {

		case 1: {
			cnt += C;

			for (int i = 0; i <= C - 4; i++) {

				if (arr[i] == arr[i + 1] && arr[i] == arr[i + 2] && arr[i] == arr[i + 3]) {
					cnt++;
				}

			}
			break;
		}
		case 2: {
			for (int i = 0; i <= C - 2; i++) {

				if (arr[i] == arr[i + 1]) {
					cnt++;
				}

			}
			break;
		}
		case 3: {

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] == arr[i + 1] && arr[i + 1] + 1 == arr[i + 2]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] == arr[i + 1] + 1) {
					cnt++;
				}
			}

			break;
		}
		case 4: {

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] == arr[i + 1] + 1 && arr[i + 1] == arr[i + 2]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] + 1 == arr[i + 1]) {
					cnt++;
				}
			}

			break;
		}
		case 5: {

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] == arr[i + 1] && arr[i] == arr[i + 2]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] + 1 == arr[i + 1]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] == arr[i + 1] + 1) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] == arr[i + 1] + 1 && arr[i] == arr[i + 2]) {
					cnt++;
				}
			}

			break;
		}
		case 6: {

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] == arr[i + 1] && arr[i] == arr[i + 2]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] == arr[i + 1]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] + 1 == arr[i + 1] && arr[i + 1] == arr[i + 2]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] == arr[i + 1] + 2) {
					cnt++;
				}
			}

			break;
		}
		case 7: {

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] == arr[i + 1] && arr[i] == arr[i + 2]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] == arr[i + 1]) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 3; i++) {
				if (arr[i] == arr[i + 1] && arr[i + 1] == arr[i + 2] + 1) {
					cnt++;
				}
			}

			for (int i = 0; i <= C - 2; i++) {
				if (arr[i] + 2 == arr[i + 1]) {
					cnt++;
				}
			}

			break;
		}

		}

		return cnt;

	}

}
