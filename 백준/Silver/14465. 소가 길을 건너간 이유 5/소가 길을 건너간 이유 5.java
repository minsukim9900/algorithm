import java.io.*;
import java.util.*;

public class Main {

	private static int N, K, B;
	private static int[] lights;
    
    private static int read() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
    

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;


		N = read();
		K = read();
		B = read();

		lights = new int[(N >> 5) + 1];

		for (int i = 0; i < B; i++) {
			int idx = read();
			lights[idx >> 5] |= 1 << (idx & 31);
		}
		
		System.out.println(cal());

	}

	private static int cal() {
		int currCnt = 0;

		for (int i = 1; i <= K; i++) {
			if ((lights[i >> 5] & (1 << (i & 31))) != 0) {
				currCnt++;
			}
		}

		int min = currCnt;

		for (int i = K + 1; i <= N; i++) {
			int a = 0;
			int b = 0;

			if ((lights[(i - K) >> 5] & (1 << ((i - K) & 31))) != 0) {
				a = 1;
			}
			if ((lights[i >> 5] & (1 << (i & 31))) != 0) {
				b = 1;
			}
			
			currCnt += b - a;
			min = Math.min(min, currCnt);
		}
		
		return min;

	}

}