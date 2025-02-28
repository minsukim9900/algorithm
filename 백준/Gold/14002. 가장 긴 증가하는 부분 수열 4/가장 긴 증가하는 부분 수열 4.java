import java.util.*;
import java.io.*;

public class Main {

	private static int N;
	private static int[] arr, lis;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		lis = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int lisMax = 0;
		int lisIdx = 0;

		for (int i = 1; i < N + 1; i++) {
			int curr = arr[i];
			int max = 0;
			int idx = 0;

			for (int j = i - 1; j > 0; j--) {

				if (arr[j] < arr[i] && max < lis[j]) {
					max = lis[j];
					idx = j;
				}

			}
			lis[i] = lis[idx] + 1;

			if (lisMax < lis[i]) {
				lisMax = lis[i];
				lisIdx = i;
			}
		}

		int result = lisMax;
		int[] array = new int[lisMax];
		array[--lisMax] = arr[lisIdx];
		int pre = arr[lisIdx];
		
		while (lisMax > 0) {

			int max = 0;
			int idx = 0;

			for (int i = lisIdx - 1; i > 0; i--) {

				if (pre > arr[i] && max < lis[i]) {
					max = lis[i];
					idx = i;
				}

			}
			
			pre = arr[idx];
			lisIdx = idx;
			array[--lisMax] = arr[idx];

		}
		
		System.out.println(result);
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

	}

}