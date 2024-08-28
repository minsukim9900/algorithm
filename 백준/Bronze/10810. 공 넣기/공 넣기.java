import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st, sy;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<M; i++) {
			sy = new StringTokenizer(br.readLine());
			
			int I = Integer.parseInt(sy.nextToken());
			int J = Integer.parseInt(sy.nextToken());
			int K = Integer.parseInt(sy.nextToken());
			
			for(int j=I-1; j<J; j++) {
				arr[j] = K;
			}
			
		}
		
		for(int k=0; k<arr.length; k++) {
			bw.write(arr[k] + " ");
		}
		bw.close();
		
	}
}