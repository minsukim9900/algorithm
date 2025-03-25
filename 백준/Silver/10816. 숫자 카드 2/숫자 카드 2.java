import java.io.*;
import java.util.*;

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
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(upperBound(num) - lowerBound(num) + " ");
		}
		sb.append("\n");
		System.out.println(sb.toString());
	}

	private static int lowerBound(int v) {
		int s = 0; // 처음 인덱스
		int e = N - 1; // 마지막 인덱스
		int result = 0; // 결과를 담을 곳

		while (s <= e) {
			int mid = s + ((e - s) >> 1);

			if (arr[mid] == v) {
				result = mid;
				e = mid - 1;
			} else if (arr[mid] < v) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return result;
	}

	private static int upperBound(int v) {
		int s = 0; // 처음 인덱스
		int e = N - 1; // 마지막 인덱스
		int result = 0; // 결과를 담을 곳
		
		while (s <= e) {
			int mid = s + ((e - s) >> 1);

			if (arr[mid] == v) {
				result = mid + 1;
				s = mid + 1;
			} else if (arr[mid] < v) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		
		return result;
	}
}