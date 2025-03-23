import java.io.*;
import java.util.*;

public class Main {
	private static long N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Long.parseLong(br.readLine());
		K = Long.parseLong(br.readLine());
		
		
		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long s = 1;
		long e = N * N;

		while (s < e) {

			long mid = (s >> 1) + (e >> 1);
			
			long cnt = count(mid);
			
			if( cnt >= K) {
				e = mid;
			}else{
				s = mid + 1;
			}
			

		}
		
		return s;

	}

	private static long count(long V) {
		long cnt = 0;

		for (int i = 1; i <= N; i++) {
			cnt += Math.min(V / i, N);
		}

		return cnt;
	}

}