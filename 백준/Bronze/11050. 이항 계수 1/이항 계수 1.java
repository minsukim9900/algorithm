import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int nFactorial = 1;
		int kFactorial = 1;
		int nMinuskFatorial = 1; // n! / (n-k)! * k!

		// nFaotrial 생성
		if (N == 0) {
			nFactorial = 1;
		} else {
			
			for (int i = N; i > 0; i--) {
				nFactorial *= i;
			}
			
		}

		// kFaotrial 생성
		if (K == 0) {
			kFactorial = 1;
		} else {
			
			for (int i = K; i > 0; i--) {
				kFactorial *= i;
			}
			
		}
		
		// nMinsukFaotrial 생성
		if ((N - K) == 0) {
			nMinuskFatorial = 1;
		} else {
			
			for (int i = N - K; i > 0; i--) {
				nMinuskFatorial *= i;
			}
			
		}
		
		System.out.println(nFactorial / (nMinuskFatorial*kFactorial));
	}
}