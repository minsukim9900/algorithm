import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] result = new int[N];

		for (int i = 0; i < N; i++) {

			int cnt = 0;
			int idx = 0;

			for (int j = 0; j < N; j++) {
				
				if(arr[i] == cnt && result[j] == 0) {
					
					result[j] = i +1;
					break;
				}
				
				if(result[j] == 0 || result[j] > i + 1) {
					cnt++;
				}
				
			}
			

		}
		
		
		for (int w : result) {
			System.out.print(w + " ");
		}

	}
}
