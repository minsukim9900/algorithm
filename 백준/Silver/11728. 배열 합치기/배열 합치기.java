import java.io.*;
import java.util.*;

public class Main {

	private static int A, B;
	private static int[] arr1, arr2;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		arr1 = new int[A];
		arr2 = new int[B];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}

		int fIdx = 0;
		int sIdx = 0;

		int[] result = new int[A + B];
		int idx = 0;
		while (fIdx != A && sIdx != B) {

			if (arr1[fIdx] > arr2[sIdx]) {
				result[idx++] = arr2[sIdx++];
			} else {
				result[idx++] = arr1[fIdx++];
			}
		}
		
		if(fIdx == A) {
			
			while(sIdx != B) {
				result[idx++] = arr2[sIdx++];
			}
			
		}else if(sIdx == B) {
			
			while(fIdx != A) {
				result[idx++] = arr1[fIdx++];
			}
		}
		
		for(int w : result) {
			sb.append(w + " ");
		}
		
		System.out.println(sb.toString());
	}

}
