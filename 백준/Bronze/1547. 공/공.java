import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = { 1, 0, 0 };
		int temp = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cupx = Integer.parseInt(st.nextToken()) - 1;
			int cupy = Integer.parseInt(st.nextToken()) - 1;
			
			temp = arr[cupx];
			arr[cupx] = arr[cupy];
			arr[cupy] = temp;
			if (cupx < 0|| cupx > 3 || cupy < 0 || cupy > 3) {
				System.out.println(-1);
			}
			
			

		}
		
		int sum =0;
		for(int i = 0; i<arr.length; i++) {
			sum+=arr[i];
			if(arr[i]==1) {
				System.out.println(i+1);
			}
		}
		
		if(sum==0) {
			System.out.println(-1);
		}

	}
}