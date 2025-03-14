import java.io.*;
import java.util.*;

public class Main {

	private static int A, B;
	private static int[] arr1, arr2;
	
	public static int read() throws IOException {
		int n = System.in.read() & 15, cur;
		boolean isNegative = (n == 13);
		if (isNegative) {
			n = System.in.read() & 15;
		}
		while ((cur = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (cur & 15);
		}
		return isNegative ? ~n + 1: n;
	}

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		A = read();
		B = read();

		arr1 = new int[A];
		arr2 = new int[B];

		for (int i = 0; i < A; i++) {
			arr1[i] = read();
		}

		for (int i = 0; i < B; i++) {
			arr2[i] = read();
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
