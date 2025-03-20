import java.io.*;
import java.util.*;

import javax.lang.model.type.UnionType;

public class Main {
	private static int N, M;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int leftSide = findNumLeft(num);
			int rightSide = findNumRight(num);
			
			int result = 0;
			if(leftSide != -1) {
				result = rightSide - leftSide + 1;
			}
			sb.append(result + " ");
		}
		
		System.out.println(sb.toString());
	}

	private static int findNumRight(int num) {
		int s = 0;
		int e = N - 1;
		int result = -1;

		while (s <= e) {
			int mid = (s + e) >> 1;
			if (arr[mid] == num) {
				result = mid;
				s = mid + 1;
			} else if (arr[mid] > num) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}
		return result;
	}

	private static int findNumLeft(int num) {
		int s = 0;
		int e = N - 1;
		int result = -1;

		while (s <= e) {
			int mid = (s + e) >> 1;

			if (arr[mid] == num) {
				result = mid;
				e = mid - 1;
			} else if (arr[mid] > num) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return result;
	}

}